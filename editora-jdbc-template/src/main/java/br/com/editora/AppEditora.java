package br.com.editora;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import br.com.editora.dao.AutorDao;
import br.com.editora.dao.EditoraDao;
import br.com.editora.dao.EnderecoDao;
import br.com.editora.dao.LivroAutorDao;
import br.com.editora.dao.LivroDao;
import br.com.editora.entity.Autor;
import br.com.editora.entity.Editora;
import br.com.editora.entity.Endereco;
import br.com.editora.entity.Livro;
import br.com.editora.entity.LivroAutor;

@EnableAutoConfiguration
@ComponentScan
public class AppEditora implements CommandLineRunner{
	
	@Autowired
	private EditoraDao editoraDao;
	@Autowired
	private LivroDao livroDao;
	@Autowired
	private LivroAutorDao livroAutorDao;
	@Autowired
	private AutorDao autorDao;
	@Autowired
	private EnderecoDao enderecoDao;
	
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
		//deleteAutor();
		//findEditoraWithAutores();
		//insertLivro();
		//findLivroWithAutores();
		//findAutorWithLivros();
		//findLivroByEdicao();
		//findLivrosByPaginas();
		//updateLivro();
		//findLivroByTituloEdicao();
		//procedureUpperCase();
		//procedureInfo();
		//functionAutorLivro();
		//insertBatchEditoras();
		//updateBatchAutores();
		//deleteBatchAutores();
		findAllEnderecos();
		
		System.out.println("--------------------------------");
	}

	private void findAllEnderecos() {
		
		List<Endereco> enderecos = enderecoDao.findAll();
		
		for(Endereco endereco:enderecos){
			System.out.println(endereco.toString());
		}
		
	}

	private void deleteBatchAutores() {
		
		autorDao.deleteBatch(Arrays.asList(8,9,10));
		
	}

	private void updateBatchAutores() {

		Autor a1 = autorDao.findById(8);
		System.out.println(a1.toString());
		a1.setNome("--GEORGE SALU--");
		
		Autor a2 = autorDao.findById(9);
		System.out.println(a2.toString());
		a2.setNome("--GEORGE SALU 2--");
		
		Autor a3 = autorDao.findById(10);
		System.out.println(a3.toString());
		a3.setNome("--GEORGE SALU 3--");
		
		
		autorDao.updateBatch(Arrays.asList(a1,a2,a3));
		
		findAll();
		
	}

	private void insertBatchEditoras() {
		
		List<Editora> editoras = new ArrayList<Editora>();
		editoras.add(new Editora("Editora Lobo", "Sao paulo", "sa@.com"));
		editoras.add(new Editora("Editora Lobo 2", "Sao paulo 2", "sa2@.com"));
		editoras.add(new Editora("Editora Lobo 3", "Sao paulo 3", "sa3@.com"));
		
		editoraDao.saveBatch(editoras);
		
		findAllEditoras();
		
		editoras = new ArrayList<Editora>();
		editoras.add(new Editora("Editora Lobo 4", "Sao paulo 4", "sa4@.com"));
		editoras.add(new Editora("Editora Lobo 5", "Sao paulo 5", "sa5@.com"));
		editoras.add(new Editora("Editora Lobo 6", "Sao paulo 6", "sa6@.com"));
		
		editoraDao.insertBacth(editoras);
		
		findAllEditoras();
	}

	private void functionAutorLivro() {

		String texto = livroDao.callFunctionTotalLivrosByAutor(3);
		
		System.out.println(texto.toString());
		
	}

	private void procedureInfo() {
		
		List<String> retorno = livroDao.callProcedureInfoLivro(1);
		
		System.out.println(retorno.toString());
		
	}

	private void procedureUpperCase() {

		Map<String,Object> map = livroDao.callProcedureUppercaseTitulo(1);
		
		for(Map.Entry<String,Object> kv: map.entrySet()){
			System.out.println(kv.getKey() + "\n"+kv.getValue());
		}
		
	}

	private void findLivroByTituloEdicao() {

		Livro livro = livroDao.findByTituloAndEdicao("Aprenda groovy", 1);

		System.out.println(livro.toString());
		
	}

	private void updateLivro() {
		
		Livro livro = livroDao.findLivroWithAutores(8);
		System.out.println(livro.toString());
		
		
		livro.setTitulo("Aprenda Liferay MVC");
		livro.setPaginas(100);
		
		//int ok = livroDao.update(livro);
		int ok = livroDao.alter(livro);
		
		if (ok == 1) {
			
			livro = livroDao.findLivroWithAutores(8);
			System.out.println(livro.toString());
			
		}else{
			System.out.println("Operacao nao realizada");
		}
		
	}

	private void findLivrosByPaginas() {

		List<Livro> livros = livroDao.findByPaginas(168,168);
		
		for(Livro livro : livros){
			System.out.println(livro.toString());
		}
		
	}

	private void findLivroByEdicao() {
		
		List<Livro> livros = livroDao.findByEdicao(1);
		
		for(Livro l: livros){
			System.out.println(l.toString());
		}
		
	}

	private void findAutorWithLivros() {

		Autor autor = autorDao.findAutorWithLivros(3);
		
		System.out.println(autor.toString());
		
		for(Livro livro : autor.getLivros()){
			System.out.println(livro.toString());
		}
		
	}

	private void findLivroWithAutores() {
		
		Livro livro = livroDao.findLivroWithAutores(8);
		
		System.out.println(livro.toString());
		
		for(Autor autor : livro.getAutores()){
			System.out.println(autor.toString());
		}
		
	}

	private void insertLivro() {
		
		String titulo = "Aprenda xamarin";
		int edicao = 1;
		int paginas = 168;
		String[] autores = {"George salu"};
		
		Livro livro = new Livro();
		livro.setTitulo(titulo);
		livro.setEdicao(edicao);
		livro.setPaginas(paginas);
		
		livro = livroDao.save(livro);
		
		Integer idLivro = livro.getId();
		
		for (String nome : autores) {
			
			System.out.println("nomes ===>  "+nome);
			
			Integer idAutor = autorDao.getIdByNome(nome);
			
			livroAutorDao.save(new LivroAutor(idLivro, idAutor));
		}

		
	}

	private void findEditoraWithAutores() {

		Editora editora = editoraDao.findEditoraWithAutores(2,0,2);
		
		System.out.println(
				editora.getId() + "," +
				editora.getRazaoSocial() + "," +
				editora.getCidade() + "," +
				editora.getEmail()
				);

		editora = editoraDao.findEditoraWithAutores(2,1,2);

		
		for (Autor autor : editora.getAutores()){
			System.out.println(
					autor.getId()+","+
					autor.getNome()+","+
					autor.getEmail());
		}
		
		System.out.println("----------------------------------------------");
		
		editora = editoraDao.findEditoraWithAutores(2,2,2);

		for (Autor autor : editora.getAutores()){
			System.out.println(
					autor.getId()+","+
					autor.getNome()+","+
					autor.getEmail());
		}

		System.out.println("----------------------------------------------");
		
		editora = editoraDao.findEditoraWithAutores(2,3,2);

		for (Autor autor : editora.getAutores()){
			System.out.println(
					autor.getId()+","+
					autor.getNome()+","+
					autor.getEmail());
		}
		
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
		
		Editora editora = editoraDao.findById(2);
		Autor autor = new Autor();
		autor.setNome("gustavo figueira");
		autor.setEmail("gustavo@gmail.com");
		autor.setEditora(editora);
		
		if(autor.getId() == null){
			autor = autorDao.save(autor);
		}
		
		if(autor.getId() != null){
			System.out.println(autor.toString());
		}
		
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
