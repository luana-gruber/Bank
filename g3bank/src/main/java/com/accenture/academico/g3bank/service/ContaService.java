package com.accenture.academico.g3bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.academico.g3bank.entity.Conta;
import com.accenture.academico.g3bank.enums.Operacao;
import com.accenture.academico.g3bank.exceptions.EmptyFieldsException;
import com.accenture.academico.g3bank.exceptions.EntityNotFoundException;
import com.accenture.academico.g3bank.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ExtratoService extratoService;
	
	
	public List<Conta> searchAll() {
		return contaRepository.findAll();
		
	}
	
	public Conta search(Long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		
		return conta.orElseThrow(() -> new EntityNotFoundException(
				"Objeto não encontrado! Id: " + id ));
	}
	
	public Conta save(Conta conta) {

		if (conta.getTipoconta() == null) {
			throw new EmptyFieldsException("O tipo da conta não foi preenchido!");
		}
		else if (conta.getNumeroConta() == null) {
			throw new EmptyFieldsException("O número da conta não foi preenchido!");
		}
		else if (conta.getSaldoConta() == null) {
			throw new EmptyFieldsException("Saldo da não foi preenchido!");
		}

		return contaRepository.save(conta);
	}
	
	public Conta update(Long id, Conta newConta) {
        Conta conta = search(newConta.getId());
        conta.setAgencia(newConta.getAgencia());
       
        return  contaRepository.save(conta);
	}
	
	public void delete(Long id) {
			contaRepository.deleteById(id);
		
	}
	
	public void withdraw(Long id, Double valor) {
		Conta conta = search(id);
		if (conta.getSaldoConta() < 0) {
			System.out.println("Saldo insuficiente");
		}
		else {
			conta.setSaldoConta(conta.getSaldoConta() - valor);
			save(conta);
			extratoService.generateExtract(conta, Operacao.SAQUE , valor);
		}
	}
	
	public void deposit(Long id, Double valor) {
		Conta conta = search(id);
		if (valor < 0) {
			System.out.println("Valor inválido");
		}
		else {
			conta.setSaldoConta(conta.getSaldoConta() + valor);
			save(conta);
			extratoService.generateExtract(conta, Operacao.DEPOSITO , valor);
		}
	}
	
	public void transfer(Long idContaOrigem, Long idContaDestino, Double valor) {
		Conta contaOrigem = search(idContaOrigem);
		Conta contaDestino = search(idContaDestino);
		Double valorContaOrigem = contaOrigem.getSaldoConta();
		
		if(valorContaOrigem < valor) {
			System.out.println("Valor inválido");
		}
		else {
			contaDestino.setSaldoConta(contaDestino.getSaldoConta() + valor);
			save(contaDestino);
			contaOrigem.setSaldoConta(valorContaOrigem - valor);
			save(contaOrigem);
			
			extratoService.generateExtract(contaOrigem, Operacao.TRANSFERENCIA , valor);
		}
		
	}
}
