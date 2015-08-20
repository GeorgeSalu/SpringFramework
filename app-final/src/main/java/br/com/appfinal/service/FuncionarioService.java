package br.com.appfinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.appfinal.dao.FuncionarioDao;
import br.com.appfinal.entity.Endereco;
import br.com.appfinal.entity.Funcionario;

@Service
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
	
	public Funcionario findById(Integer id){
		return dao.findById(id);
	}
	
	public List<Funcionario> findAll(){
		return dao.findAll();
	}
}
