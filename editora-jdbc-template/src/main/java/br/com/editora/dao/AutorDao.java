package br.com.editora.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.editora.entity.Autor;

@Repository
public class AutorDao {

	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert; 
	
	
	public Autor save(Autor autor){
		
		SqlParameterSource parameterSource = new MapSqlParameterSource()
									.addValue("NOME", autor.getNome())
									.addValue("EMAIL", autor.getEmail())
									.addValue("ID_EDITORA", autor.getEditora().getId());
		
		Number key = simpleJdbcInsert.executeAndReturnKey(parameterSource);
		
		autor.setId(key.intValue());
		
		return autor;
		
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
