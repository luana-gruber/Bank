package com.accenture.academico.g3bank.Init;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.accenture.academico.g3bank.entity.Agencia;
import com.accenture.academico.g3bank.entity.Cliente;
import com.accenture.academico.g3bank.entity.Conta;
import com.accenture.academico.g3bank.enums.TipoConta;
import com.accenture.academico.g3bank.repository.AgenciaRepository;
import com.accenture.academico.g3bank.repository.ClienteRepository;
import com.accenture.academico.g3bank.repository.ContaRepository;
import com.accenture.academico.g3bank.service.ContaService;
import com.accenture.academico.g3bank.util.ValorOperacao;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	AgenciaRepository agenciaRepo;
	
	@Autowired
	ContaRepository contaRepo;

	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	ContaService contaService;
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ValorOperacao valor = new ValorOperacao();
		Conta conta1 = new Conta(null, "1234", TipoConta.CORRENTE, 1400.00);
		Conta conta2 = new Conta(null, "5678", TipoConta.CORRENTE, 1300.00);
		Conta conta3 = new Conta(null, "9111", TipoConta.POUPANCA, 1000.00);
		Conta conta4 = new Conta(null, "2345", TipoConta.POUPANCA, 500.00);
		Agencia agencia = new Agencia(null, "G3 Campinas", "Rua Osvaldo Cruz, 1999", "199999-9999", 24, Arrays.asList(conta1, conta2));
		Agencia agencia1 = new Agencia(null, "G3 São Bernardo", "Rua Marechal Deodoro, 345", "219999-9999", 20, Arrays.asList(conta3));
		Agencia agencia2 = new Agencia(null, "G3 São Paulo", "Rua Vinte e Cinco de Março, 24", "219999-8888", 18, Arrays.asList(conta4));
		Cliente cliente1 = new Cliente(null, "Luana", "35877842307", 999999999, "luana@gmail.com", conta1);
		Cliente cliente2 = new Cliente(null, "Kerollen", "32467552489", 888888888, "kerollen@gmail.com", conta2);
		Cliente cliente3 = new Cliente(null, "João", "61697218555", 777777777, "joao@gmail.com", conta3);
		Cliente cliente4 = new Cliente(null, "Kayke", "38519814808", 666666666, "kayke@gmail.com", conta4);
		
		agenciaRepo.saveAll(Arrays.asList(agencia, agencia1, agencia2));
		contaRepo.saveAll(Arrays.asList(conta1, conta2, conta3, conta4));
		clienteRepo.saveAll(Arrays.asList(cliente1, cliente2, cliente3, cliente4));
		
		conta1.setAgencia(agencia);
		conta2.setAgencia(agencia);
		conta3.setAgencia(agencia1);
		conta4.setAgencia(agencia2);
		conta1.setCliente(cliente1);
		conta2.setCliente(cliente2);
		conta3.setCliente(cliente3);
		conta4.setCliente(cliente4);
		agenciaRepo.saveAll(Arrays.asList(agencia, agencia1));
		contaRepo.saveAll(Arrays.asList(conta1, conta2, conta3, conta4));
		clienteRepo.saveAll(Arrays.asList(cliente1, cliente2, cliente3, cliente4));
		
		
		valor.setValor(200.00);
		contaService.deposit(conta1.getId(), valor);
		valor.setValor(50.00);
		contaService.withdraw(conta1.getId(), valor);
		valor.setValor(100.00);
		contaService.transfer(conta1.getId(), conta2.getId(), valor);

	}
}
