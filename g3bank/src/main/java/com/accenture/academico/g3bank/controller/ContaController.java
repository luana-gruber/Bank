package com.accenture.academico.g3bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academico.g3bank.entity.Conta;
import com.accenture.academico.g3bank.repository.ContaRepository;

@Controller
@RestController
public class ContaController {
	@Autowired
    private ContaRepository contaRepository;
	
	 @GetMapping(value = "/conta")
	    public List<Conta> Get() {
	        return contaRepository.findAll();
	    }
	 
	 @GetMapping(value = "/conta/{id}")
	    public ResponseEntity<Conta> GetById(@PathVariable(value = "id") Integer id)
	    {
	        Optional<Conta> conta = contaRepository.findById(id);
	        if(conta.isPresent())
	            return new ResponseEntity<Conta>(conta.get(), HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 
	 @PostMapping(value = "/conta")
	    public Conta Post(@Valid @RequestBody Conta conta)
	    {
	        return contaRepository.save(conta);
	    }
	    
	   @DeleteMapping(value = "/conta/{id}")
	    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
	    {
	        Optional<Conta> conta = contaRepository.findById(id);
	        if(conta.isPresent()){
	            contaRepository.delete(conta.get());
	            return new ResponseEntity<>(HttpStatus.OK);
	        }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
}

