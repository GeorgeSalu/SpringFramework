package br.com.appfinal.entity;

public class Cargo {

	private Integer idCargo;
	private String cargo;
	private Integer idDepartamento;
	private Departamento departamento;

	public Integer getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Integer getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Cargo [idCargo=" + idCargo + ", cargo=" + cargo
				+ ", idDepartamento=" + idDepartamento + ", departamento="
				+ departamento + "]";
	}

}
