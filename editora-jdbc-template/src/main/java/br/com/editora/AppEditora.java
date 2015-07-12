package br.com.editora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import br.com.editora.dao.EditoraDao;

@EnableAutoConfiguration
@ComponentScan
public class AppEditora implements CommandLineRunner{
	
	public static void main(String[] args) {

		SpringApplication sp = new SpringApplication();
		sp.run(AppEditora.class);
		
	}

	@Override
	public void run(String... arg0) throws Exception {

		System.out.println("Start Spring Boot");
		
	}
	
}
