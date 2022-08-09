package com.accenture.academico.g3bank.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.accenture.academico.g3bank.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private String numeroConta;
	private Double saldoConta = 0.0;
	
	@Enumerated(EnumType.STRING)
	private TipoConta tipoconta;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_agencia")
	private Agencia agencia;
	
	public Conta() {
		
	}
	
	public Conta(Long id, String numeroConta, TipoConta tipoConta, Double saldoConta) {
		this.id = id;
		this.numeroConta = numeroConta;
		this.saldoConta = saldoConta;
		this.tipoconta = tipoConta;
	}
	
	public Conta(Long id, String numeroConta, TipoConta tipoConta, Double saldoConta, Agencia agencia) {
		this.id = id;
		this.numeroConta = numeroConta;
		this.saldoConta = saldoConta;
		this.agencia = agencia;
		this.tipoconta = tipoConta;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public Double getSaldoConta() {
		return saldoConta;
	}
	
	public void setSaldoConta(Double saldoConta) {
		this.saldoConta = saldoConta;
	}

	public TipoConta getTipoconta() {
		return tipoconta;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id!= null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

//	public void saque(Double valor, Date dataHoraMovimento) {
//		if (this.saldoConta < 0) {
//			System.out.println("Saldo insuficiente");
//		}
//		this.saldoConta -= valor;
//		this.dataHoraMovimento = dataHoraMovimento;
//	}
//	
//	
//	public void deposito(Double valor, Date dataHoraMovimento) {
//		this.saldoConta += valor;
//		this.dataHoraMovimento = dataHoraMovimento;
//	}
	
//	public void tranferencia(Double valor, Date dataHoraMovimento, Integer idContaDestino) {
//		if(idContaDestido == this.numeroConta.isPresent ) {
//			
//		}
//	}

}


