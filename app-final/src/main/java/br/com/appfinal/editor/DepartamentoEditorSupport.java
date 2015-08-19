package br.com.appfinal.editor;

import java.beans.PropertyEditorSupport;

import br.com.appfinal.entity.Departamento;
import br.com.appfinal.service.DepartamentoService;

public class DepartamentoEditorSupport extends PropertyEditorSupport{

	private DepartamentoService service;
	
	public DepartamentoEditorSupport(DepartamentoService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		
		if(!text.isEmpty()){
			Integer id = Integer.parseInt(text);
			
			Departamento departamento = service.findById(id);
			
			super.setValue(departamento);
		}
		
	}
	
}
