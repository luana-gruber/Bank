package com.accenture.academico.g3bank.entity;

public class ContaPoupanca extends Conta {
	
	private Double rendimento;
	
	public ContaPoupanca(Integer id, String numeroConta, Double saldoConta, Integer senhaCliente, Double rendimento) {
		super(id, numeroConta, saldoConta);
		this.rendimento = rendimento;
	}
	
	public Double getRendimento() {
		return rendimento;
	}
	
}
