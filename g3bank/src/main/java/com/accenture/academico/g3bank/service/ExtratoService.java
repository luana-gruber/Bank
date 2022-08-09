package com.accenture.academico.g3bank.service;

import java.time.LocalDateTime;
import java.util.Calendar;
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
	
	public Extrato search(Long id) {
		Optional<Extrato> conta = extratoRepository.findById(id);
		
		return conta.orElseThrow(() -> new EntityNotFoundException(
				"Extrato não encontrado! Id: " + id ));
	}
	
	public void generateExtract(Conta conta, Operacao operacao, Double valor) {
		Calendar data = Calendar.getInstance();
		
		Extrato extrato = new Extrato();
		extrato.setConta(conta);
		extrato.setTipoOperacao(operacao);
		extrato.setValor(valor);
		extrato.setData(data);
		save(extrato);
	}
}
