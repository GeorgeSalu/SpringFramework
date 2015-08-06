package br.com.editora.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.editora.dao.mapper.LivroMapper;
import br.com.editora.entity.Autor;
import br.com.editora.entity.Editora;
import br.com.editora.entity.Livro;

@Repository
@PropertySource("classpath:sql/livro.xml")
public class LivroDao {
	
	//@Autowired
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameter;
	
	@Autowired
	public LivroDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameter = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Value("${sql.livro.findLivroWithAutores}")
	private String SQL_FIND_LIVRO_WITH_AUTORES;
	@Value("${sql.livro.findByEdicao}")
	private String SQL_FIND_BY_EDICAO;
	@Value("${sql.livro.findByPaginas}")
	private String SQL_FIND_BY_PAGINAS;
	@Value("${sql.livro.update}")
	private String SQL_UPDATE;
	@Value("${sql.livro.findByTituloAndEdicao}")
	private String SQL_FIND_BY_TITULO_AND_EDICAO;
	
	
	public int alter(Livro livro){
		
		return namedParameter.update(SQL_UPDATE, 
								new BeanPropertySqlParameterSource(livro));
		
	}
	
	
	public Livro findByTituloAndEdicao(String titulo,int edicao){
		
		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setEdicao(edicao);
		
		return namedParameter.queryForObject(
							SQL_FIND_BY_TITULO_AND_EDICAO, 
							new BeanPropertySqlParameterSource(livro),
							new LivroMapper());
		
	}
	
	
	public List<Livro> findByPaginas(int min,int max){
		
		SqlParameterSource parameters = new MapSqlParameterSource("minimo",min)
												.addValue("maximo", max);
		
		
		return namedParameter.query(
					SQL_FIND_BY_PAGINAS, 
					parameters,
					new LivroMapper());
		
	}
	
	
	public int update(Livro livro){
		
		SqlParameterSource parameters = new MapSqlParameterSource("id",livro.getId())
												.addValue("titulo", livro.getTitulo())
												.addValue("edicao", livro.getEdicao())
												.addValue("paginas", livro.getPaginas());
		
		return namedParameter.update(SQL_UPDATE, parameters);
		
	}
	
	
	public List<Livro> findByEdicao(int edicao){
		
		return namedParameter.query(
				SQL_FIND_BY_EDICAO, 
				new MapSqlParameterSource("edicao", edicao),
				new LivroMapper());
		
	}
	
	public Livro save(Livro livro) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
			.withTableName("LIVROS")
			.usingColumns("TITULO", "EDICAO", "PAGINAS")
			.usingGeneratedKeyColumns("ID_LIVRO");
		
		Number key = insert.executeAndReturnKey(
				new BeanPropertySqlParameterSource(livro));
		
		livro.setId(key.intValue());
		
		return livro;
	}
	
	public Livro findLivroWithAutores(int idLivro){
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(
				SQL_FIND_LIVRO_WITH_AUTORES,
				idLivro);
		
		Livro livro = null;
		
		List<Autor> autores = new ArrayList<Autor>();
		
		for(Map row: rows){
			
			if(livro == null){
				livro = new Livro();
				livro.setId((Integer) row.get("ID_LIVRO"));
				livro.setTitulo((String) row.get("TITULO"));
				livro.setEdicao((Integer) row.get("EDICAO"));
				livro.setPaginas((Integer) row.get("PAGINA"));
			}
			
			Autor autor = new Autor();
			autor.setId((Integer) row.get("ID_AUTOR"));
			autor.setNome((String) row.get("NOME"));
			autor.setEmail((String) row.get("AUTOR_EMAIL"));
			
			Editora editora = new Editora();
			editora.setId((Integer) row.get("ID_EDITORA"));
			editora.setRazaoSocial((String) row.get("RAZAO_SOCIAL"));
			editora.setCidade((String) row.get("CIDADE"));
			editora.setEmail((String) row.get("EMAIL"));
			
			autor.setEditora(editora);
			autores.add(autor);
		}
		
		livro.setAutores(autores);
		
		return livro;
	}
	
}
