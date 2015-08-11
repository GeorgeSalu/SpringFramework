package br.com.appfinal.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("br.com.appfinal")
@PropertySource("classpath:datasource.properties")
public class DatabaseConfig {
	
	@Value("${datasource.url}")
	private String url;
	@Value("${datasource.username}")
	private String username;
	@Value("${datasource.password}")
	private String password;
	@Value("${datasource.driverClassName}")
	private String driverClassName;
	
	/* configuracao nescessaria para o spring pegar os arquivos de properties com a anotacao @PropertySource */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySource(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/* configuração do datasource */
	@Bean
	public DataSource dataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setUrl(url);
		ds.setDriverClassName(driverClassName);
		return ds;
	}
	
	/* pega uma instancia de JdbcTemplate passando um datasource configurado  */
	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource());
	}
	
}
