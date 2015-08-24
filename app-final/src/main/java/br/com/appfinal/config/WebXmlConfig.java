package br.com.appfinal.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebXmlConfig implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext webContext = 
				new AnnotationConfigWebApplicationContext();
		
		webContext.register(MvcConfig.class);
		webContext.setServletContext(servletContext);
		
		DispatcherServlet dispatcher = new DispatcherServlet(webContext);
		
		dispatcher.setThrowExceptionIfNoHandlerFound(true);
		
		ServletRegistration.Dynamic reDynamic = 
				servletContext.addServlet("dispacher", dispatcher);
		
		reDynamic.setLoadOnStartup(1);
		reDynamic.addMapping("/");
		
	}

}
