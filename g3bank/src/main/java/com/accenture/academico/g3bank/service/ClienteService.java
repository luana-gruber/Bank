package com.accenture.academico.g3bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.academico.g3bank.entity.Cliente;
import com.accenture.academico.g3bank.exceptions.EmptyFieldsException;
import com.accenture.academico.g3bank.exceptions.EntityNotFoundException;
import com.accenture.academico.g3bank.exceptions.IntegratyViolationException;
import com.accenture.academico.g3bank.exceptions.InvalidFieldException;
import com.accenture.academico.g3bank.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente save(Cliente cliente) {
		Cliente clienteBuscado = null;

		try {
			clienteBuscado = searchCpf(cliente.getCpfCliente());
			return clienteBuscado;
			
		} catch (EntityNotFoundException e) {
			if (cliente.getNomeCliente().isEmpty()) {
				throw new EmptyFieldsException("Nome do cliente não foi preenchido!");
			}
			else if (cliente.getEmailCliente().isEmpty()) {
				throw new EmptyFieldsException("E-mail do cliente não foi preenchido!");
			}
			else if (cliente.getCpfCliente().isEmpty()) {
				throw new EmptyFieldsException("CPF do cliente não foi preenchido!");
			}
			else if (cliente.getTelefoneCliente() == null) {
				throw new EmptyFieldsException("Telefone do cliente não foi preenchido!");
			}
			return clienteRepository.save(cliente);
		}
	}
	
	public Cliente searchCpf(String cpfCliente) {
		validCpf(cpfCliente);

		Optional<Cliente> cliente = clienteRepository.findByCpf(cpfCliente);

		return cliente.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado! CPF: " + cpfCliente));
	}
	
	public List<Cliente> searchAll() {
		return (List<Cliente>) clienteRepository.findAll();
		
	}
	public boolean validCpf(String cpfCliente) {
		 int[] cpfValido = new int[11];
	   	 String letra;
	   	 for (int i = 0; i < cpfCliente.length(); i++) {
	   		 letra = String.valueOf(cpfCliente.charAt(i));
	   		 cpfValido[i] = Integer.parseInt(letra);
	   	 }
	   	 int [] peso = {11,10,9,8,7,6,5,4,3,2};
	        int primeirodigito = 0, segundodigito = 0;
	        int resultado1 = cpfValido[0]*peso[1] + cpfValido[1]*peso[2] + cpfValido[2]*peso[3] +
	                         cpfValido[3]*peso[4] + cpfValido[4]*peso[5] + cpfValido[5]*peso[6] +
	                         cpfValido[6]*peso[7] + cpfValido[7]*peso[8] + cpfValido[8]*peso[9];
	        int resultado2 = cpfValido[0]*peso[0] + cpfValido[1]*peso[1] + cpfValido[2]*peso[2] +
	                         cpfValido[3]*peso[3] + cpfValido[4]*peso[4] + cpfValido[5]*peso[5] +
	                         cpfValido[6]*peso[6] + cpfValido[7]*peso[7] + cpfValido[8]*peso[8] +
	                         cpfValido[9]*peso[9];


	        int resto1 = resultado1 % 11;
	        int resto2 = resultado2 % 11;

	        if (resto1 < 2){
	            primeirodigito = 0;
	            } else {
	                primeirodigito = 11 - resto1;
	            }
	        if (resto2 < 2){
	            segundodigito = 0;
	            } else {
	                segundodigito = 11 - resto2;
	            }
	        if (primeirodigito == cpfValido[9] && segundodigito == cpfValido[10]){
	            return true;
	        }
			else
	        	throw new InvalidFieldException("CPF inválido!");
	        }

	    
	
	public Cliente updateClient(String cpfCliente, Cliente newCliente) {
		validCpf(cpfCliente);

		Cliente cliente = searchCpf(newCliente.getCpfCliente());
		cliente.setEmailCliente(newCliente.getEmailCliente());
        cliente.setTelefoneCliente(newCliente.getTelefoneCliente());

		return clienteRepository.save(cliente);
	}

	public void delete(String cpfCliente) {
		validCpf(cpfCliente);
		Cliente cliente = searchCpf(cpfCliente);
		
		if (cliente.getConta() == null) {
			clienteRepository.deleteById(cliente.getId());
		}
		else  {
			throw new IntegratyViolationException("Não é possível excluir o cliente que possuí conta!");
		}
		
	}
}


