package br.com.appfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.appfinal.entity.Departamento;
import br.com.appfinal.service.DepartamentoService;

@Controller
@RequestMapping("departamento")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView findAll(@ModelAttribute("departamento") Departamento departamento,
			ModelMap model){
		
		model.addAttribute("departamentos",service.findAll());
		
		return new ModelAndView("addDepartamento",model);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@ModelAttribute("departamento") Departamento departamento){
		
		service.saveOrUpdate(departamento);
		
		return "redirect:/departamento/add";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public ModelAndView preUpdate(@PathVariable("id") Integer id,ModelMap model){
		
		Departamento d = service.findById(id);
		model.addAttribute("idDepartamento",id);
		model.addAttribute("departamento", d.getDepartamento());
		
		return new ModelAndView("redirect:/departamento/add",model);
	}
	
	@RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable("id") Integer id){
		
		service.delete(id);

		return "redirect:/departamento/add";
	}
	
}
