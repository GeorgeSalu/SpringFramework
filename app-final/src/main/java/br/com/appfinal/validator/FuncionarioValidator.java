package br.com.appfinal.validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.appfinal.entity.Funcionario;

public class FuncionarioValidator implements Validator{

	private EnderecoValidator enderecoValidator;
	
	public FuncionarioValidator(EnderecoValidator enderecoValidator) {
		this.enderecoValidator = enderecoValidator;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Funcionario.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", 
				"error.nome","O campo nome e obrigatorio");
		
		Funcionario f = (Funcionario) target;
		
		if(f.getSalario() != null){
			if(f.getSalario() < 0){
				errors.rejectValue("salario", "error.salario","O salario nao deve ser negativo");
			}
		}else{
			errors.rejectValue("salario","error.salario", "O campo salario e obrigatorio");
		}
		
		if(f.getDataEntrada() != null){
			Date atual = new Date();
			if(f.getDataEntrada().after(atual)){
				errors.rejectValue("dataEntrada", "error.dataEntrada", "A data de Entrada deve ser anterior ou igual a data atual");
			}
		}else{
			errors.rejectValue("dataEntrada","error.dataEntrada", "O campo data Entrada deve ser Informada");
		}
		
		if(f.getDataSaida() != null){
			if(f.getDataSaida().before(f.getDataEntrada())){
				errors.rejectValue("dataSaida", "error.dataSaida", "O campo data Saida deve ser posterior a data atual");
			}
		}
		
		if(f.getCargo() == null){
			errors.rejectValue("cargo", "error.cargo", "O campo cargo deve ser selecionado");
		}

		ValidationUtils.invokeValidator(enderecoValidator, f.getEndereco(), errors);
		
	}

}
