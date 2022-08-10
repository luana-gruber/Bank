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
		Conta conta2 = new Conta(null, "2345", TipoConta.CORRENTE, 1300.00);
		Conta conta3 = new Conta(null, "1233", TipoConta.POUPANCA, 1000.00);
		Cliente cliente1 = new Cliente(null, "Luana", "35877842307", 99999999, "luana@gmail.com", conta1);
		Cliente cliente2 = new Cliente(null, "Kerollen", "32467552489", 99999999, "kerollen@gmail.com", conta2);
		Agencia agencia = new Agencia(null, "NovaG3", "Rua Osvaldo Cruz, 1999", "(19)9999-9999", 24, Arrays.asList(conta1, conta2));
		
		agenciaRepo.saveAll(Arrays.asList(agencia));
		contaRepo.saveAll(Arrays.asList(conta1, conta2, conta3));
		clienteRepo.save(cliente1);
		clienteRepo.save(cliente2);
		conta1.setAgencia(agencia);
		conta2.setAgencia(agencia);
		conta1.setCliente(cliente1);
		conta2.setCliente(cliente2);
		contaRepo.saveAll(Arrays.asList(conta1, conta2));
		
		
		valor.setValor(200.00);
		contaService.deposit(conta1.getId(), valor);
		valor.setValor(50.00);
		contaService.withdraw(conta1.getId(), valor);
		valor.setValor(100.00);
		contaService.transfer(conta1.getId(), conta2.getId(), valor);

	}
}
