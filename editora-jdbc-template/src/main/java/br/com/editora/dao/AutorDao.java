package br.com.editora.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.editora.dao.mapper.AutorMapper;
import br.com.editora.entity.Autor;

@Repository
@PropertySource("classpath:sql/autor.xml")
public class AutorDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert; 
	
	@Autowired
	private EditoraDao editoraDao;
	@Value("${sql.autor.findAll}")
	private String SQL_FIND_ALL;
	@Value("${sql.autor.findAutoresBy.editora}")
	private String SQL_FIND_AUTORES_BY_EDITORA;
	@Value("${sql.autor.update}")
	private String SQL_UPDATE;
	@Value("${sql.autor.findBy.id}")
	private String SQL_FIND_BY_ID;
	@Value("${sql.autor.delete}")
	private String SQL_DELETE;
	
	public int delete(int id){
		
		return jdbcTemplate.update(SQL_DELETE,id);
		
	}
	
	public int update(Autor autor){
		
		return jdbcTemplate.update(
				SQL_UPDATE, 
				autor.getNome(),autor.getEmail(),autor.getEditora().getId(),autor.getId());
		
	}
	
	public Autor findById(int id){
		
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new AutorMapper(editoraDao),id);
		
	}
	
	public List<Autor> findAutoresByEditora(String razaoSocial){
		
		return jdbcTemplate.query(
				SQL_FIND_AUTORES_BY_EDITORA,
				new AutorMapper().new AutorWithEditoraMapper(), 
				razaoSocial);
		
	}
	
	public Autor save(Autor autor){
		
		SqlParameterSource parameterSource = new MapSqlParameterSource()
									.addValue("NOME", autor.getNome())
									.addValue("EMAIL", autor.getEmail())
									.addValue("ID_EDITORA", autor.getEditora().getId());
		
		Number key = simpleJdbcInsert.executeAndReturnKey(parameterSource);
		
		autor.setId(key.intValue());
		
		return autor;
		
	}
	
	
	public List<Autor> findAll(){
		
		return jdbcTemplate.query(SQL_FIND_ALL, new AutorMapper(editoraDao));
		
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
								.withTableName("AUTORES")
								//.usingColumns("NOME","EMAIL","ID_EDITORA")
								.usingGeneratedKeyColumns("ID_AUTOR");
	}
	
	
	
}
