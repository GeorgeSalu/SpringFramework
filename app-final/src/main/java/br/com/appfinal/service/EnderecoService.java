package br.com.appfinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.appfinal.dao.EnderecoDao;
import br.com.appfinal.entity.Endereco;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EnderecoService {

	@Autowired
	private EnderecoDao enderecoDao;
	
	@Transactional(rollbackFor = Exception.class)
	public Endereco save(Endereco endereco){
		if(endereco.getIdEndereco() == null){
			endereco = enderecoDao.save(endereco);
		}else{
			enderecoDao.update(endereco);
		}
		return endereco;
	}
	
	public void delete(Integer id){
		enderecoDao.delete(id);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Endereco findById(Integer id){
		return enderecoDao.findById(id);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Endereco> findAll(){
		return enderecoDao.findAll();
	}
}
