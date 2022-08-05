package com.accenture.academico.g3bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academico.g3bank.entity.Agencia;
import com.accenture.academico.g3bank.repository.AgenciaRepository;

import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping(path = "/g3bank")
public class AgenciaController {
	
	@Autowired
    private AgenciaRepository agenciaRepository;
	
	 @RequestMapping(value = "/agencia", method = RequestMethod.GET)
	 @ApiOperation(value="Retorna uma lista de agências")
	    public List<Agencia> Get() {
	        return agenciaRepository.findAll();
	    }
	 
	 @RequestMapping(value = "/agencia/{id}", method = RequestMethod.GET)
	 @ApiOperation(value="Retorna uma agência única")
	    public ResponseEntity<Agencia> GetById(@PathVariable(value = "id") Integer id)
	    {
	        Optional<Agencia> agencia = agenciaRepository.findById(id);
	        if(agencia.isPresent())
	            return new ResponseEntity<Agencia>(agencia.get(), HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 
	 @RequestMapping(value = "/agencia", method =  RequestMethod.POST)
	 @ApiOperation(value="Salva uma agência")
	    public Agencia Post(@Valid @RequestBody Agencia agencia)
	    {
	        return agenciaRepository.save(agencia);
	    }
	 
	 @RequestMapping(value = "/agencia/{id}", method =  RequestMethod.PUT)
	 @ApiOperation(value="Atualiza uma agência")
	    public ResponseEntity<Agencia> Put(@PathVariable(value = "id") Integer id, @Valid @RequestBody Agencia newAgencia)
	    {
	        Optional<Agencia> oldAgencia = agenciaRepository.findById(id);
	        if(oldAgencia.isPresent()){
	            Agencia agencia = oldAgencia.get();
	            agencia.setEnderecoAgencia(newAgencia.getEnderecoAgencia());
	            agencia.setTelefoneAgencia(newAgencia.getTelefoneAgencia());
	            agenciaRepository.save(agencia);
	            return new ResponseEntity<Agencia>(agencia, HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	   @RequestMapping(value = "/agencia/{id}", method = RequestMethod.DELETE)
	   @ApiOperation(value="Deleta uma agência")
	    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
	    {
	        Optional<Agencia> agencia = agenciaRepository.findById(id);
	        if(agencia.isPresent()){
	            agenciaRepository.delete(agencia.get());
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
}
