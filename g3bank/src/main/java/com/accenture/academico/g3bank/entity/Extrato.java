package com.accenture.academico.g3bank.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.accenture.academico.g3bank.enums.Operacao;

@Entity
public class Extrato implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHoraMovimento;
	
	@Column(nullable = true)
	private Double valor;
	private String informacoes; 
	private Double saldoAnterior = 0.0;
	
	@Enumerated(EnumType.STRING)
	private Operacao tipoOperacao;
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;
	
	public Extrato() {
	}
	public Extrato(Calendar dataHoraMovimento, Operacao tipoOperacao, Double valor, Conta conta, Double saldoAnterior) {
		this.dataHoraMovimento = dataHoraMovimento;
		this.tipoOperacao = tipoOperacao;
		this.valor = valor;
		this.conta = conta;
		this.saldoAnterior = saldoAnterior;
	}
	
	public Long getId() {
		return id;
	}

	public Calendar getData() {
		return dataHoraMovimento;
	}
	
	public void setData(Calendar dataHoraMovimento) {
		this.dataHoraMovimento = dataHoraMovimento;
	}
	
	public Operacao getTipoOperacao() {
		return tipoOperacao;
	}
	
	public void setTipoOperacao(Operacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Conta getConta() {
		return conta;
	}
	
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public String getInformacoes() {
		return informacoes;
	}
	
	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}
	
	public Double getSaldoAnterior() {
		return saldoAnterior;
	}
	public void setSaldoAnterior(Double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		Extrato other = (Extrato) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
	
