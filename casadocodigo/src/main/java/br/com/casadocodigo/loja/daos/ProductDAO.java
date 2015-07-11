package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Products;

@Repository
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager;
	
	public void save (Products products){
		manager.persist(products);
	}

	public List<Products> list() {
		return manager.createQuery(
				"select p from Products p",
				Products.class).getResultList();
	}

	public Products find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
