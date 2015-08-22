package br.com.appfinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.appfinal.dao.FuncionarioDao;
import br.com.appfinal.entity.Endereco;
import br.com.appfinal.entity.Funcionario;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FuncionarioService {

	@Autowired
	private FuncionarioDao dao;
	@Autowired
	private EnderecoService enderecoService;
	
	public Funcionario saveOrUpdate(Funcionario funcionario){
		Endereco endereco = enderecoService.save(funcionario.getEndereco());
		funcionario.setEndereco(endereco);
		
		if(funcionario.getIdFuncionario() == null){
			dao.save(funcionario);
		}else{
			dao.update(funcionario);
		}
		return funcionario;
	}
	
	public void delete(Integer id){
		dao.delete(id);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Funcionario findById(Integer id){
		return dao.findById(id);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Funcionario> findAll(){
		return dao.findAll();
	}
}
