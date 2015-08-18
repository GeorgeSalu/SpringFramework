package br.com.appfinal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.appfinal.dao.CargoDao;
import br.com.appfinal.entity.Cargo;

@Service
public class CargoService {

	@Autowired
	private CargoDao cargoDao;
	
	public void saveOrUpdate(Cargo cargo){
		if(cargo.getIdCargo() == null){
			cargoDao.save(cargo);
		}else{
			cargoDao.update(cargo);
		}
	}
	
	public void delete(Integer id){
		cargoDao.delete(id);
	}
	
	public Cargo findById(Integer id){
		return cargoDao.findById(id);
	}
	
	public List<Cargo> findAll(){
		return cargoDao.findAll();
	}
	
}
