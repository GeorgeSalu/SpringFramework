package br.com.appfinal.editor;

import java.beans.PropertyEditorSupport;

import br.com.appfinal.entity.Cargo;
import br.com.appfinal.service.CargoService;

public class CargoEditorSupport extends PropertyEditorSupport{

	private CargoService service;

	public CargoEditorSupport(CargoService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(!text.isEmpty()){
			Integer id = Integer.parseInt(text);
			Cargo cargo = service.findById(id);
			
			super.setValue(cargo);;
		}
		
		
	}
	
}
