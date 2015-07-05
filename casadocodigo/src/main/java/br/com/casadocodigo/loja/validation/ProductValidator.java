package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Products;

public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return Products.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"title", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"description", "field.required");
		
		Products products = (Products) target;
		
		if(products.getPages() == 0){
			errors.rejectValue("pages","field.required");
		}
		
	}

	
	
}
