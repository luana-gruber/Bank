package com.accenture.academico.g3bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.academico.g3bank.entity.Agencia;
import com.accenture.academico.g3bank.exceptions.EmptyFieldsException;
import com.accenture.academico.g3bank.exceptions.EntityNotFoundException;
import com.accenture.academico.g3bank.exceptions.IntegratyViolationException;
import com.accenture.academico.g3bank.repository.AgenciaRepository;

@Service
public class AgenciaService {
	
	@Autowired
	private AgenciaRepository agenciaRepository;
	
	
	public List<Agencia> searchAll() {
		return agenciaRepository.findAll();
		
	}
	public Agencia search(Long id) {
		Optional<Agencia> agencia = agenciaRepository.findById(id);
		
		return agencia.orElseThrow(() -> new EntityNotFoundException(
				"Objeto não encontrado! Id: " + id ));
	}
	
	public Agencia save(Agencia agencia) {
		if (agencia.getNomeAgencia().isEmpty()) {
			throw new EmptyFieldsException("Nome da agência não foi preenchido!");
		}
		else if (agencia.getEnderecoAgencia().isEmpty()) {
			throw new EmptyFieldsException("Endereçoda agência não foi preenchido!");
		}
		else if (agencia.getTelefoneAgencia().isEmpty()) {
			throw new EmptyFieldsException("Telefone da agência não foi preenchido!");
		}
		else if (agencia.getNumeroAgencia() == null) {
			throw new EmptyFieldsException("Número da agência não foi preenchido!");
		}
		return agenciaRepository.save(agencia);
	}
	
	public Agencia update(Long id, Agencia newAgencia) {
            Agencia agencia = search(newAgencia.getId());
            agencia.setEnderecoAgencia(newAgencia.getEnderecoAgencia());
            agencia.setTelefoneAgencia(newAgencia.getTelefoneAgencia());
           
            return  agenciaRepository.save(agencia);
    }
	
	public void delete(Long id) {
		Agencia agencia = search(id);
		
		if (agencia.getContas().isEmpty()) {
			agenciaRepository.deleteById(id);
		}
		else  {
			throw new IntegratyViolationException("Não é possível excluir a agência que possuí contas!");
		}
	}
}
