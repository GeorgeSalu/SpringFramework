package br.com.appfinal.entity;

import java.util.Calendar;

public class Funcionario {

	private Integer idFuncionario;
	private String nome;
	private Calendar dataEntrada;
	private Calendar dataSaida;
	private Double salario;
	private Cargo cargo;
	private Endereco endereco;

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Calendar dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Calendar getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Funcionario [idFuncionario=" + idFuncionario + ", nome=" + nome
				+ ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida
				+ ", salario=" + salario + ", cargo=" + cargo + ", endereco="
				+ endereco + "]";
	}

}
