package com.accenture.academico.g3bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.academico.g3bank.entity.Conta;
import com.accenture.academico.g3bank.enums.Operacao;
import com.accenture.academico.g3bank.exceptions.BusinessRuleException;
import com.accenture.academico.g3bank.exceptions.EmptyFieldsException;
import com.accenture.academico.g3bank.exceptions.EntityNotFoundException;
import com.accenture.academico.g3bank.exceptions.IntegratyViolationException;
import com.accenture.academico.g3bank.exceptions.InvalidFieldException;
import com.accenture.academico.g3bank.repository.ContaRepository;
import com.accenture.academico.g3bank.util.ValorOperacao;

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
				"Conta não encontrada! Id: " + id ));
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
        if (newConta.getAgencia() == null) {
			throw new EmptyFieldsException("A agência não foi preenchida!");
		}
        else {
        	return  contaRepository.save(conta);
        }
	}
	
	public void delete(Long id) {
		Conta conta = search(id);
		
		if (conta.getAgencia() == null && conta.getCliente() == null) {
			contaRepository.deleteById(id);
		}
		else  {
			throw new IntegratyViolationException("Não é possível excluir a conta que possuí agência e cliente!");
		}
		
	}
	
	public void withdraw(Long id, ValorOperacao valor) {
		Conta conta = search(id);
		Double valorSaque = valor.getValor();
		if (conta.getSaldoConta() <= 0 || conta.getSaldoConta() < valorSaque) {
			throw new BusinessRuleException("Saldo insuficiente");
		}
		if (valorSaque < 0) {
			throw new InvalidFieldException("Valor do saque é negativo");
		}
		else {
			conta.setSaldoConta(conta.getSaldoConta() - valorSaque);
			save(conta);
			extratoService.generateExtract(false, conta, Operacao.SAQUE , valorSaque, null, conta.getSaldoConta());
		}
	}
	
	public void deposit(Long id, ValorOperacao valor) {
		Conta conta = search(id);
		Double valorDeposito = valor.getValor();
		if (valorDeposito < 0) {
			throw new InvalidFieldException("Valor do depósito é negativo");
		}
		else {
			conta.setSaldoConta(conta.getSaldoConta() + valorDeposito);
			save(conta);
			extratoService.generateExtract(true, conta, Operacao.DEPOSITO , valorDeposito, null, conta.getSaldoConta());
		}
	}
	
	public void transfer(Long idContaOrigem, Long idContaDestino, ValorOperacao valor) {
		Conta contaOrigem = search(idContaOrigem);
		Conta contaDestino = search(idContaDestino);
		Double valorTransferencia = valor.getValor();
		Double saldoContaOrigem = contaOrigem.getSaldoConta();
		
		if(saldoContaOrigem < valorTransferencia) {
			throw new BusinessRuleException("Saldo insuficiente");
		}
		if (valorTransferencia < 0) {
			throw new InvalidFieldException("Valor da transferência é negativo");
		}
		else {
			contaDestino.setSaldoConta(contaDestino.getSaldoConta() + valorTransferencia);
			save(contaDestino);
			contaOrigem.setSaldoConta(saldoContaOrigem - valor.getValor());
			save(contaOrigem);
			
			extratoService.generateExtract(false, contaOrigem, Operacao.TRANSFERENCIA , valorTransferencia, contaDestino, contaOrigem.getSaldoConta());
			extratoService.generateExtract(true, contaDestino, Operacao.TRANSFERENCIA , valorTransferencia, contaOrigem, contaDestino.getSaldoConta());
		}
		
	}
}
