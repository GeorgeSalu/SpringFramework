package br.com.casadocodigo.loja.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Products;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	@Autowired
	private ProductDAO productDAO; 
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products",productDAO.list());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String save(Products product){
		productDAO.save(product);
		System.out.println("Cadastrando o produtos"+product);
		return "products/ok";
	}
	
	@RequestMapping("/form")
	public ModelAndView form(){
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
}
