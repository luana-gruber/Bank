package com.accenture.academico.g3bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agencia {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	 
	 @Column(nullable = true)
	 private String nomeAgencia;
	 private String enderecoAgencia;
	 private String telefoneAgencia;
	 private Integer numeroAgencia;
	 
	//Se não houver dá erro no MySql
	public Agencia() {
	}

	public Agencia(Integer id, String nomeAgencia, String enderecoAgencia, String telefoneAgencia, Integer numeroAgencia) {
		this.id = id;
		this.nomeAgencia = nomeAgencia;
		this.enderecoAgencia = enderecoAgencia;
		this.telefoneAgencia = telefoneAgencia;
		this.numeroAgencia = numeroAgencia;
	}

	public Integer getId() {
		return id;
	}

	public String getNomeAgencia() {
		return nomeAgencia;
	}

	public String getEnderecoAgencia() {
		return enderecoAgencia;
	}

	public void setEnderecoAgencia(String enderecoAgencia) {
		this.enderecoAgencia = enderecoAgencia;
	}

	public String getTelefoneAgencia() {
		return telefoneAgencia;
	}

	public void setTelefoneAgencia(String telefoneAgencia) {
		this.telefoneAgencia = telefoneAgencia;
	}

	public Integer getNumeroAgencia() {
		return numeroAgencia;
	}
	 
}
