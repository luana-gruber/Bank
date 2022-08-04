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
	 
}
