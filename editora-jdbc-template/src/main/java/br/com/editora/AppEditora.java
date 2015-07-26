package br.com.editora;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import br.com.editora.dao.AutorDao;
import br.com.editora.dao.EditoraDao;
import br.com.editora.dao.LivroDao;
import br.com.editora.entity.Autor;
import br.com.editora.entity.Editora;

@EnableAutoConfiguration
@ComponentScan
public class AppEditora implements CommandLineRunner{
	
	@Autowired
	private EditoraDao editoraDao;
	@Autowired
	private LivroDao livroDao;
	@Autowired
	private AutorDao autorDao;
	
	public static void main(String[] args) {

		SpringApplication sp = new SpringApplication();
		sp.run(AppEditora.class);
		
	}

	@Override
	public void run(String... arg0) throws Exception {

		System.out.println("--------------------------------");
		
		//inserEditora();
		//findAllEditoras();
		//findByIdEditoras();
		//finByCidadesEditoras();
		//findByRazaoSocialEditora();
		//countEditora();
		//findEmailByIdEditora();
		//findCidadeAndEmailByIdEditora();
		//updateEditora();
		//deleteEditora();
		//execute();
		//insertAutor();
		//findAll();
		//findAutoresByEditora();
		//updateAutor();
		deleteAutor();
		
		System.out.println("--------------------------------");
	}

	private void deleteAutor() {

		int ok = autorDao.delete(1);
		
		if(ok == 1){
			System.out.println("Operacao realizada com sucesso");
		}else{
			System.out.println("Operacao nao realizada");
		}
		
	}

	private void updateAutor() {

		Autor autor = autorDao.findById(1);
		
		Editora editora = new Editora();
		editora.setId(2);
		
		autor.setEditora(editora);
		
		int ok = autorDao.update(autor);
		
		if(ok == 1){
			autor = autorDao.findById(1);
			System.out.println(autor.toString());
		}else{
			System.out.println("Operacao nao realizada");
		}
		
	}

	private void findAutoresByEditora() {

		List<Autor> autores = autorDao.findAutoresByEditora("Editora sul da Ltda.");
		
		for(Autor autor : autores){
			System.out.println(autor.toString());
		}
		
	}

	private void findAll() {
		
		List<Autor> autors = autorDao.findAll();
		
		for(Autor autor : autors){
			System.out.println(autor.toString());
		}
		
	}

	private void insertAutor() {
		
		Editora editora = editoraDao.findById(1);
		Autor autor = new Autor();
		autor.setNome("Nazare salu");
		autor.setEmail("nazare@gmail.com");
		autor.setEditora(editora);
		
		if(autor.getId() == null){
			autor = autorDao.save(autor);
		}
		
		if(autor.getId() != null){
			System.out.println(autor.toString());
		}
		
	}

	private void execute() {

	 livroDao.insert();
	 
	}

	private void deleteEditora() {
		
		int ok = editoraDao.delete(4);
		
		if(ok == 1){
			System.out.println("operacao realizada com sucesso");
		}else{
			System.out.println("Operacao nao realizada");
		}
		
	}

	private void updateEditora() {

		Editora editora = editoraDao.findById(2);
		System.out.println(editora.toString());
		
		editora.setRazaoSocial("Editora de curitiba");
		int ok = editoraDao.update(editora);

		if(ok == 1){
			Editora ed = editoraDao.findById(2);
			System.out.println(ed.toString());
		}else{
			System.out.println("operacao nao realizada");
		}
		
	}

	private void findCidadeAndEmailByIdEditora() {
		
		List<String> lista = editoraDao.findCidadeAndEmailById(3);
		
		for(String s: lista){
			System.out.println(s);
		}
		
	}

	private void findEmailByIdEditora() {

		String email = editoraDao.findByEmailId(2);
		System.out.println("email -> "+email);
			
		List<String> emails = editoraDao.findEmails();
		
		for(String s : emails){
			System.out.println("Email "+s);
		}
		
	}

	private void countEditora() {

		int count = editoraDao.count();
		System.out.println("COUNT -> "+count);
		
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
