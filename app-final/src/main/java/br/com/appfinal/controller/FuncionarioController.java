package br.com.appfinal.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.appfinal.editor.CargoEditorSupport;
import br.com.appfinal.editor.StringToDoubleEditorSupport;
import br.com.appfinal.editor.StringToIntegerEditorSupport;
import br.com.appfinal.entity.Cargo;
import br.com.appfinal.entity.Funcionario;
import br.com.appfinal.service.CargoService;
import br.com.appfinal.service.EnderecoService;
import br.com.appfinal.service.FuncionarioService;
import br.com.appfinal.validator.EnderecoValidator;
import br.com.appfinal.validator.FuncionarioValidator;

@Controller
@RequestMapping("funcionario")
public class FuncionarioController {

	private static Logger logger = Logger.getLogger(FuncionarioController.class);
	
	@Autowired
	private FuncionarioService funcionarioService;
	@Autowired
	private CargoService cargoService;
	@Autowired
	private EnderecoService enderecoService;
	
	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Cargo.class, new CargoEditorSupport(cargoService));
	
		binder.registerCustomEditor(Integer.class, "endereco.numero",new StringToIntegerEditorSupport());
		binder.registerCustomEditor(Double.class, "salario",new StringToDoubleEditorSupport());
		
		binder.addValidators(new FuncionarioValidator(new EnderecoValidator()));
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView findAll(@ModelAttribute("funcionario") Funcionario funcionario){
		
		ModelAndView modelAndView = new ModelAndView("addFuncionario");
		modelAndView.addObject("funcionarios", funcionarioService.findAll());
		modelAndView.addObject("cargos", cargoService.findAll());
		
		return modelAndView;
	}
	
	@RequestMapping(value="/save")
	public ModelAndView save(@ModelAttribute("funcionario") @Validated Funcionario funcionario,
			BindingResult result,ModelMap model){

		
		if(result.hasErrors()){
			logger.warn("Foram encontrados campos invalidos");
			model.addAttribute("funcionarios", funcionarioService.findAll());
			model.addAttribute("cargos", cargoService.findAll());
			return new ModelAndView("addFuncionario",model);
		}
		
		
		try {
			logger.info("executando saveOrUpdate para funcionario");
			
			funcionarioService.saveOrUpdate(funcionario);
		
			logger.info("Operacao realizada com sucesso");
		} catch (Exception e) {
			logger.error("Um error ao inserir/alterar um funcionario "+e);
		}
		
		return new ModelAndView("redirect:/funcionario/add");
	}
	
	@RequestMapping(value="/update/{id}")
	public ModelAndView preUpdate(@PathVariable("id") Integer id,ModelMap model){

		model.addAttribute("funcionario", funcionarioService.findById(id));
		model.addAttribute("funcionarios", funcionarioService.findAll());
		model.addAttribute("cargos", cargoService.findAll());
		
		return new ModelAndView("addFuncionario");
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable("id") Integer id){
		
		Funcionario f = funcionarioService.findById(id);
		
		enderecoService.delete(f.getEndereco().getIdEndereco());
		
		return "redirect:/funcionario/add";
	}
	
	@RequestMapping(value= "/find/cargo/{idCargo}", method = RequestMethod.GET)
	public ModelAndView findByCargo(@PathVariable("idCargo") Integer idCargo,
						@ModelAttribute("funcionario") Funcionario funcionario,
						ModelMap model){
		
		model.addAttribute("funcionarios", funcionarioService.findByCargo(idCargo));
		model.addAttribute("cargos", cargoService.findAll());
		
		return new ModelAndView("addFuncionario",model);
	}
	
	@RequestMapping(value="/find/nome/{nome}")
	public ModelAndView findByNome(@PathVariable("nome") String nome,
			@ModelAttribute("funcionario") Funcionario funcionario,
			ModelMap model){
		
		model.addAttribute("funcionarios", funcionarioService.findByNome(nome) );
		model.addAttribute("cargos", cargoService.findAll());
		
		return new ModelAndView("addFuncionario",model);
	}
	
}
