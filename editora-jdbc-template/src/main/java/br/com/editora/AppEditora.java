package br.com.editora;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import br.com.editora.dao.EditoraDao;
import br.com.editora.entity.Editora;

@EnableAutoConfiguration
@ComponentScan
public class AppEditora implements CommandLineRunner{
	
	@Autowired
	private EditoraDao editoraDao;
	
	public static void main(String[] args) {

		SpringApplication sp = new SpringApplication();
		sp.run(AppEditora.class);
		
	}

	@Override
	public void run(String... arg0) throws Exception {

		System.out.println("--------------------------------");
		
		inserEditora();
		//findAllEditoras();
		//findByIdEditoras();
		//finByCidadesEditoras();
		//findByRazaoSocialEditora();
		
		System.out.println("--------------------------------");
	}

	private void findByRazaoSocialEditora() {
		
		List<Editora> editoras = editoraDao.findByRazaoSocial("rio");
		
		for(Editora editora: editoras){
			System.out.println(editora.toString());
		}
		
	}

	private void finByCidadesEditoras() {
		
		List<Editora> editoras = editoraDao.findByCidade("Manaus", "rio de janeiro");
		
		for(Editora editora : editoras){
			System.out.println(editora.toString());
		}
	}

	private void findByIdEditoras() {
		
		Editora editora = editoraDao.findById(1);
		System.out.println(editora.toString());
		
	}

	private void findAllEditoras() {
		
		List<Editora> editoras = new ArrayList<Editora>();
		
		editoras = editoraDao.findAll();
		
		for(Editora ed: editoras){
			System.out.println(ed.toString());
		}
		
	}

	private void inserEditora() {
		
		Editora editora = new Editora();
		editora.setRazaoSocial("Salgado filho.");
		editora.setCidade("porto alegre");
		editora.setEmail("contato@gavea.com.br");
		
		int ok = editoraDao.insert(editora);
		
		//editora = editoraDao.add(editora);
		
		System.out.println("ok = "+ok);
	}
	
}
