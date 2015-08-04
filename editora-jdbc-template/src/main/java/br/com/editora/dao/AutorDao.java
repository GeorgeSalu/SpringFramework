package br.com.editora.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import br.com.editora.entity.Editora;
import br.com.editora.entity.Livro;

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
	@Value("${sql.autor.getIdByNome}")
	private String SQL_GET_ID_BY_NOME;
	@Value("${sql.autor.findAutorWithLivros}")
	private String SQL_FIND_AUTOR_WITH_LIVROS;
	
	
	public Autor findAutorWithLivros(int idAutor){
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(
									SQL_FIND_AUTOR_WITH_LIVROS,
									idAutor);
		
		Autor autor = null;
		
		List<Livro> livros = new ArrayList<Livro>();
		
		for(Map row: rows){
			
			if(autor == null){
				autor = new Autor();
				autor.setId((Integer) row.get("ID_AUTOR"));
				autor.setNome((String) row.get("NOME"));
				autor.setEmail((String) row.get("EMAIL"));
				
				Editora editora = new Editora();
				editora.setId((Integer) row.get("ID_EDITORA"));
				editora.setRazaoSocial((String) row.get("RAZAO_SOCIAL"));
				editora.setCidade((String) row.get("CIDADE"));
				editora.setEmail((String) row.get("ED_EMAIL"));
				
				autor.setEditora(editora);
			}
			
			Livro livro = new Livro();
			livro.setId((Integer) row.get("ID_LIVRO"));
			livro.setTitulo((String) row.get("TITULO"));
			livro.setEdicao((Integer) row.get("EDICAO"));
			livro.setPaginas((Integer) row.get("PAGINA"));

			livros.add(livro);
		}
		
		
		autor.setLivros(livros);
		
		return autor;
	}
	
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

	public Integer getIdByNome(String nome) {
		return jdbcTemplate.queryForObject(SQL_GET_ID_BY_NOME, Integer.class,nome);
	}
	
}
