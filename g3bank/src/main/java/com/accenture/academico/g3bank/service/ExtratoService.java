package com.accenture.academico.g3bank.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.academico.g3bank.entity.Conta;
import com.accenture.academico.g3bank.entity.Extrato;
import com.accenture.academico.g3bank.enums.Operacao;
import com.accenture.academico.g3bank.exceptions.EntityNotFoundException;
import com.accenture.academico.g3bank.repository.ExtratoRepository;
import com.accenture.academico.g3bank.util.Data;

@Service
public class ExtratoService {
	
	@Autowired
	private ExtratoRepository extratoRepository;
	
	@Autowired
	private Data dataUtil;
	
	public Extrato save(Extrato extrato) {
		return extratoRepository.save(extrato);
	}
	
	public List<Extrato> search(Conta conta) {
			List<Extrato> extratos = extratoRepository.findByConta(conta);
			return Optional.ofNullable(extratos).orElseThrow(() -> new EntityNotFoundException("Extrato não encontrado para essa conta! id: " + conta.getId()));
	}
	
	public void generateExtract(Boolean credito, Conta conta, Operacao operacao, Double valor, Conta contaDestino) {
		Calendar data = Calendar.getInstance();
		
		Extrato extrato = new Extrato(data, operacao, valor, conta);
		extrato.setInformacoes(generateInform(credito, conta, operacao, valor, contaDestino, data));
		extratoRepository.save(extrato);
	}
	
	private String generateInform(Boolean credito, Conta conta, Operacao tipo, Double valor, Conta contaDestino, Calendar data) {
		if(credito) {
			return generateCredit(contaDestino, tipo, valor, conta, data);
		}else{
			return generateDebt(conta, tipo, valor, contaDestino, data);
		}
	}

	private String generateDebt(Conta conta, Operacao tipo, Double valor, Conta contaDestino, Calendar data) {
		if(Operacao.TRANSFERENCIA.equals(tipo)) {
			return String.format("DATA: %s\n"
					+ "TRANFERÊNCIA REALIZADA DE R$ %.2f\n"
					+ "PARA %s, CONTA: %s AG: %s", dataUtil.dataFormatada(data.getTime()), valor, contaDestino.getCliente().getNomeCliente().split(" ")[0],
					contaDestino.getNumeroConta(), contaDestino.getAgencia().getNomeAgencia());
		}else {
			return String.format("DATA: %s\n"
					+ "SAQUE DE R$ %.2f", dataUtil.dataFormatada(data.getTime()), valor);
		}
	}

	private String generateCredit(Conta conta, Operacao tipo, Double valor, Conta contaDestino, Calendar data) {
		if(Operacao.TRANSFERENCIA.equals(tipo)) {
			return String.format("DATA: %s\n"
					+ "TRANFERÊNCIA RECEBIDA DE R$ %.2f\n"
					+ "POR %s, CONTA: %s AG: %s", dataUtil.dataFormatada(data.getTime()), valor, conta.getCliente().getNomeCliente().split(" ")[0],
					conta.getNumeroConta(), conta.getAgencia().getNomeAgencia());
		}else {
			return String.format("DATA: %s\n"
					+ "DEPÓSITO DE R$ %.2f", dataUtil.dataFormatada(data.getTime()), valor);
		}
	}

}
