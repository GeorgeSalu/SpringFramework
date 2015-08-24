package br.com.appfinal.entity;

import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class Funcionario {

	private Integer idFuncionario;
	private String nome;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date dataEntrada;
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date dataSaida;
	//@NumberFormat(style=Style.CURRENCY,pattern="###,###.00")
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

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
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
