package com.accenture.academico.g3bank.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity
public class Agencia {
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer id;
	 
	 @Column
	 @NotNull
	 private String nomeAgencia;
	 @NotNull
	 private String enderecoAgencia;
	 @NotNull
	 private String telefoneAgencia;
	 @NotNull
	 private Integer numeroAgencia;
	 
	 @OneToMany(targetEntity=Conta.class,cascade = CascadeType.ALL, mappedBy = "agencia")
	 private List<Conta> contas;

	 public Agencia() {
	}
	 public Agencia(Integer id, String nomeAgencia, String enderecoAgencia,
			String telefoneAgencia, Integer numeroAgencia,  List<Conta> contas) {
		this.id = id;
		this.nomeAgencia = nomeAgencia;
		this.enderecoAgencia = enderecoAgencia;
		this.telefoneAgencia = telefoneAgencia;
		this.numeroAgencia = numeroAgencia;
		this.contas = contas;
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
	
	 public List<Conta> getContas() {
		return contas;
	}

}
