package br.com.editora.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.editora.dao.mapper.EditoraMapper;
import br.com.editora.entity.Editora;

@Repository
//@PropertySource("classpath:application.properties")
@PropertySource("classpath:sql/editora.xml")
public class EditoraDao {

	@Value("${sql.insert}")
	private String SQL_INSERT;
	@Value("${sql.count}")
	private String SQL_COUNT;
	@Value("${sql.findEmailBy.id}")
	private String SQL_FIND_EMAIL_BY_ID;
	@Value("${sql.findEmails}")
	private String SQL_FIND_EMAILS;
	@Value("${sql.findCidadeAndEmailBy.id}")
	private String SQL_FIND_CIDADE_AND_EMAIL_BY_ID;
	@Value("${sql.update}")
	private String SQL_UPDATE;
	@Value("${sql.delete}")
	private String SQL_DELETE;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public int update(Editora editora){
		
		return jdbcTemplate.update(
				SQL_UPDATE, 
				editora.getRazaoSocial(),
				editora.getCidade(),
				editora.getEmail(),
				editora.getId()
				);
		
	}
	
	
	public int delete(int id){
		
		return jdbcTemplate.update(SQL_DELETE, id);
		
	}
	
	public List<String> findCidadeAndEmailById(int id){
		
		return jdbcTemplate.queryForObject(
				SQL_FIND_CIDADE_AND_EMAIL_BY_ID, 
				new Integer[] {id},
				new RowMapper<List<String>>(){

					@Override
					public List<String> mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						String cidade = rs.getString("CIDADE");
						String email = rs.getString("EMAIL");
						
						return Arrays.asList(cidade,email);
					}
					
				});
		
	}
	
	public List<String> findEmails(){
		
		return jdbcTemplate.queryForList(SQL_FIND_EMAILS,String.class);
		
	}
	
	public String findByEmailId(int id){
		
		return jdbcTemplate.queryForObject(SQL_FIND_EMAIL_BY_ID, String.class,id);
		
	}
	
	public int count(){
		
		return jdbcTemplate.queryForObject(SQL_COUNT, Integer.class);
		
	}
	
	
	public List<Editora> findByRazaoSocial(String rz){
		
		String sql = "SELECT * FROM EDITORAS WHERE RAZAO_SOCIAL LIKE '%' ? '%' ";
		
		return jdbcTemplate.query(sql, new String[] {rz}, new EditoraMapper());
	}
	
	
	public Editora findById(int id){
		
		String sql = "SELECT * FROM EDITORAS WHERE ID_EDITORA = ?";
		
		return jdbcTemplate.queryForObject(sql, new EditoraMapper(), id);
	}
	
	public List<Editora> findByCidade(String c1,String c2){
		
		String sql = "SELECT * FROM EDITORAS WHERE CIDADE LIKE ? OR CIDADE LIKE ?";
		
		return jdbcTemplate.query(sql, new EditoraMapper(), c1,c2);
	}
	
	public List<Editora> findAll(){
		
		String sql = "SELECT * FROM EDITORAS";
		
		return jdbcTemplate.query(sql, new EditoraMapper());
		
	}
	
	public Editora add(Editora editora){
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.setTableName("EDITORAS");
		insert.setColumnNames(Arrays.asList("RAZAO_SOCIAL","CIDADE","EMAIL"));
		insert.setGeneratedKeyName("ID_EDITORA");
		
		Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(editora));
		
		editora.setId(key.intValue());
		
		return editora;
	}
	
	
	public Integer save(Editora editora){
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.setTableName("EDITORAS");
		insert.setColumnNames(Arrays.asList("RAZAO_SOCIAL","CIDADE","EMAIL"));
		insert.setGeneratedKeyName("ID_EDITORA");
		
		Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(editora));
		
		return key.intValue();
	}
	
	/* o metodo update retorna 1 quando o banco form modificado e 0 quando tiver algum erro */
	public int insert(Editora editora){
		
		String sql = "INSERT INTO EDITORAS (RAZAO_SOCIAL,CIDADE,EMAIL) VALUES (?,?,?)";
		
		return jdbcTemplate.update(SQL_INSERT,
					editora.getRazaoSocial(),
					editora.getCidade(),
					editora.getEmail());
		
	}
	
}
