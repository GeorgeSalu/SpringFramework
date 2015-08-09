package br.com.editora.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.editora.entity.Endereco;

@Repository
public class EnderecoDao extends JdbcDaoSupport{

	@Autowired
	public EnderecoDao(DataSource dataSource){
		setDataSource(dataSource); 
	}
	
	public List<Endereco> findAll(){
		
		String sql = "SELECT * FROM ENDERECOS";
		
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Endereco>(Endereco.class));
	}
	
}
