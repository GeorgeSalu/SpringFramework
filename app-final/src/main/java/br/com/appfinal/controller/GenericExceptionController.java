package br.com.appfinal.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GenericExceptionController {

	private static Logger logger = Logger.getLogger(GenericExceptionController.class);
	
	private static String DEFAULT_PAGE_ERRO = "error";

	@ExceptionHandler({DataIntegrityViolationException.class})
	public ModelAndView integrityKeyException(HttpServletRequest req,DataIntegrityViolationException ex){
		
		logger.error("Request : "+req.getRequestURI()+" lançou a ex "+ex);

		ModelAndView model = new ModelAndView(DEFAULT_PAGE_ERRO);
		model.addObject("mensagem", "Impossivel inserir, este registro ja existe no banco de dados");
		model.addObject("exception",ex);
		model.addObject("url", req.getRequestURL());
		
		return model;
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public ModelAndView sqlException(HttpServletRequest req, Exception ex){
		
		logger.error("Request : "+req.getRequestURI()+" lançou a ex "+ex);

		ModelAndView model = new ModelAndView(DEFAULT_PAGE_ERRO);
		model.addObject("mensagem", "Ocorreu algum problema ao acessar o banco de dados");
		model.addObject("exception",ex);
		model.addObject("url", req.getRequestURL());
		
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView defaultException(HttpServletRequest req, Exception ex){
		
		logger.error("Request : "+req.getRequestURI()+" lançou a ex "+ex);

		ModelAndView model = new ModelAndView(DEFAULT_PAGE_ERRO);
		model.addObject("mensagem", "Uma exceção ocorreu tente novamente");
		model.addObject("exception",ex);
		model.addObject("url", req.getRequestURL());
		
		return model;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView notFoundException(HttpServletRequest req, Exception ex){
		
		logger.error("Request : "+req.getRequestURI()+" lançou a ex "+ex);

		ModelAndView model = new ModelAndView(DEFAULT_PAGE_ERRO);
		model.addObject("mensagem", "Ops! esta pagina nao existe");
		model.addObject("exception",ex);
		model.addObject("url", req.getRequestURL());
		
		return model;
	}
	
}
