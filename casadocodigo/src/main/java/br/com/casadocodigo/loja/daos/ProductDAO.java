package br.com.casadocodigo.loja.daos;

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
	
}
