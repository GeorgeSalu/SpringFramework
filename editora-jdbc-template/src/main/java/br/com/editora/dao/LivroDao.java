package br.com.editora.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.com.editora.entity.Livro;

@Repository
public class LivroDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
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
	
}
