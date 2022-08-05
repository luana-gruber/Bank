package com.accenture.academico.g3bank.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private String nomeCliente;
	private String cpfCliente;
	private Integer telefoneCliente;
	private String emailCliente;

	public Cliente() {
		
	}
	
	public Cliente(Integer id, String nomeCliente, String cpfCliente, Integer telefoneCliente, String emailCliente) {
		this.id = id;
		this.cpfCliente = cpfCliente;
		this.nomeCliente = nomeCliente;
		this.telefoneCliente = telefoneCliente;
		this.emailCliente = emailCliente;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public Integer getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(Integer telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	
	
	public void setCpfCliente(String cpfCliente) {
	   	 int[] cpfValido = new int[11];
	   	 String letra;
	   	 for (int i = 0; i < cpfCliente.length(); i++) {
	   		 letra = String.valueOf(cpfCliente.charAt(i));
	   		 cpfValido[i] = Integer.parseInt(letra);
	   	 }
	   	 int [] peso = {11,10,9,8,7,6,5,4,3,2};
	        int primeirodigito = 0, segundodigito = 0;
	        int resultado1 = cpfValido[0]*peso[1] + cpfValido[1]*peso[2] + cpfValido[2]*peso[3] +
	                         cpfValido[3]*peso[4] + cpfValido[4]*peso[5] + cpfValido[5]*peso[6] +
	                         cpfValido[6]*peso[7] + cpfValido[7]*peso[8] + cpfValido[8]*peso[9];
	        int resultado2 = cpfValido[0]*peso[0] + cpfValido[1]*peso[1] + cpfValido[2]*peso[2] +
	                         cpfValido[3]*peso[3] + cpfValido[4]*peso[4] + cpfValido[5]*peso[5] +
	                         cpfValido[6]*peso[6] + cpfValido[7]*peso[7] + cpfValido[8]*peso[8] +
	                         cpfValido[9]*peso[9];


	        int resto1 = resultado1 % 11;
	        int resto2 = resultado2 % 11;

	        if (resto1 < 2){
	            primeirodigito = 0;
	            } else {
	                primeirodigito = 11 - resto1;
	            }
	        if (resto2 < 2){
	            segundodigito = 0;
	            } else {
	                segundodigito = 11 - resto2;
	            }
	        if (primeirodigito == cpfValido[9] && segundodigito == cpfValido[10]){
	            this.cpfCliente = cpfCliente;
	        }
	    }


	}




