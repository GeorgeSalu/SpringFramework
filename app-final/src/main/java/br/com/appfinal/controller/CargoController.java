package br.com.appfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.appfinal.editor.DepartamentoEditorSupport;
import br.com.appfinal.entity.Cargo;
import br.com.appfinal.entity.Departamento;
import br.com.appfinal.service.CargoService;
import br.com.appfinal.service.DepartamentoService;

@Controller
@RequestMapping("cargo")
public class CargoController {

	private static final int PAGE_DEFAULT = 1;
	
	private static final int PAGE_SIZE_DEFAULT = 5;
	
	@Autowired
	private CargoService cargoService;
	@Autowired
	private DepartamentoService departamentoService;
	
	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Departamento.class, 
				new DepartamentoEditorSupport(departamentoService));
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView findAll(@ModelAttribute("cargo") Cargo cargo,ModelMap model){
		
		model.addAttribute("cargos", cargoService.findByPage(PAGE_DEFAULT, PAGE_SIZE_DEFAULT));
		model.addAttribute("departamentos", departamentoService.findAll());
		model.addAttribute("current",PAGE_DEFAULT);
		model.addAttribute("total", cargoService.getTotalPages(PAGE_SIZE_DEFAULT));
		
		return new ModelAndView("addCargo",model);
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(@ModelAttribute("cargo") Cargo cargo){
		
		cargoService.saveOrUpdate(cargo);
		
		return "redirect:/cargo/add";
	}
	
	@RequestMapping(value="/update/{id}")
	public ModelAndView preUpdate(@PathVariable("id") Integer id){
		
		Cargo cargo = cargoService.findById(id);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addCargo");
		modelAndView.addObject("cargo", cargo);
		modelAndView.addObject("cargos",cargoService.findByPage(PAGE_DEFAULT, PAGE_SIZE_DEFAULT));
		modelAndView.addObject("departamentos", departamentoService.findAll());
		modelAndView.addObject("current",PAGE_DEFAULT);
		modelAndView.addObject("total", cargoService.getTotalPages(PAGE_SIZE_DEFAULT));
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Integer id){
		cargoService.delete(id);
		return "redirect:/cargo/add";
	}
	
	@RequestMapping(value="/page/{page}")
	public ModelAndView paginatio(@PathVariable("page") Integer page,@ModelAttribute("cargo") Cargo cargo){
		
		ModelAndView model = new ModelAndView("addCargo");
		model.addObject("departamentos", departamentoService.findAll());
		
		model.addObject("cargos", cargoService.findByPage(page, PAGE_SIZE_DEFAULT));
		model.addObject("current",page);
		model.addObject("total", cargoService.getTotalPages(PAGE_SIZE_DEFAULT));
		
		return model;
	}
	
}
