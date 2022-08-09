package com.accenture.academico.g3bank.Init;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.accenture.academico.g3bank.entity.Agencia;
import com.accenture.academico.g3bank.entity.Cliente;
import com.accenture.academico.g3bank.entity.Conta;
import com.accenture.academico.g3bank.repository.AgenciaRepository;
import com.accenture.academico.g3bank.repository.ClienteRepository;
import com.accenture.academico.g3bank.repository.ContaRepository;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	AgenciaRepository agenciaRepo;
	
	@Autowired
	ContaRepository contaRepo;

	@Autowired
	ClienteRepository clienteRepo;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Conta conta1 = new Conta(null, "1234", 1400.00, 123456);
		Conta conta2 = new Conta(null, "2345", 1500.00, 654321);
		Cliente cliente1 = new Cliente(null, "Luana", "35877842307", 99999999, "luana@gmail.com" );
		Agencia agencia = new Agencia(null, "NovaG3", "Rua Osvaldo Cruz, 1999", "(19)9999-9999", 24, Arrays.asList(conta1, conta2));
		
		conta1.setAgencia(agencia);
		conta2.setAgencia(agencia);
		agenciaRepo.saveAll(Arrays.asList(agencia));
		contaRepo.saveAll(Arrays.asList(conta1));
		clienteRepo.save(cliente1);
	}
}
