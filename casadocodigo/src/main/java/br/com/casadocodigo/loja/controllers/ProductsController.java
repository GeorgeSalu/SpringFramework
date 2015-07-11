package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.servlet.http.Part;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Products;
import br.com.casadocodigo.loja.validation.ProductValidator;

@Controller
@Transactional
@RequestMapping("/produtos")
public class ProductsController {

	/*
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new ProductValidator());
	}*/
	
	@Autowired
	private ProductDAO productDAO; 
	
	@Autowired
	private FileSaver fileSaver;
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("products/list");
		
		List<Products> produtos = productDAO.list();
		
		System.out.println(produtos.size());
		modelAndView.addObject("products",productDAO.list());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView save(MultipartFile summary,@Valid Products product,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes){
		
		
		if(bindingResult.hasErrors()){
			return form(product);
		}

		
		String webPath = fileSaver.write("WEB-INF/imagens", summary);
		System.out.println(webPath);
		product.setSummaryPath(webPath);
		
		
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");

		return new ModelAndView("redirect:produtos");
	
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Products products){
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("products/show");
		Products products = productDAO.find(id);
		modelAndView.addObject("product",products);
		return modelAndView;
	}
	
}
