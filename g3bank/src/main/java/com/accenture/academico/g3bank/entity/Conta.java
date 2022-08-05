package com.accenture.academico.g3bank.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.accenture.academico.g3bank.enums.TipoConta;

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = true)
	private String numeroConta;
	private Double saldoConta = 0.0;
	private TipoConta tipoconta;
	private Integer senhaCliente;
	private Date dataHoraMovimento;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_agencia")
	private Agencia agencia;
	
	public Conta() {
		
	}
	
	public Conta(Integer id, String numeroConta, Double saldoConta, Integer senhaCliente) {
		super();
		this.id = id;
		this.numeroConta = numeroConta;
		this.saldoConta = saldoConta;
		this.senhaCliente = senhaCliente;
	}

	public Integer getId() {
		return id;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public Double getSaldoConta() {
		return saldoConta;
	}

	public TipoConta getTipoconta() {
		return tipoconta;
	}

	public Integer getSenhaCliente() {
		return senhaCliente;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public void saque(Double valor, Date dataHoraMovimento) {
		if (this.saldoConta < 0) {
			System.out.println("Saldo insuficiente");
		}
		this.saldoConta -= valor;
		this.dataHoraMovimento = dataHoraMovimento;
	}
	
	
	public void deposito(Double valor, Date dataHoraMovimento) {
		this.saldoConta += valor;
		this.dataHoraMovimento = dataHoraMovimento;
	}
	
//	public void tranferencia(Double valor, Date dataHoraMovimento, Integer idContaDestino) {
//		if(idContaDestido == this.numeroConta.isPresent ) {
//			
//		}
//	}

}


