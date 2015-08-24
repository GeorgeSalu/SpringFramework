package br.com.appfinal.editor;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;

public class StringToIntegerEditorSupport extends PropertyEditorSupport{

	private static Logger logger = Logger.getLogger(StringToIntegerEditorSupport.class);
	
	@Override
	public void setAsText(String text) {
		
		try {
			super.setValue(Integer.parseInt(text));
		} catch (NumberFormatException ex) {
			logger.fatal("O campo numero esperava um inteiro e recebeu uma string ",ex);
		}
		
	}
	
}
