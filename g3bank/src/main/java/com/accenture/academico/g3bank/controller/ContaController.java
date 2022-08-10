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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.academico.g3bank.entity.Agencia;
import com.accenture.academico.g3bank.entity.Cliente;
import com.accenture.academico.g3bank.entity.Conta;
import com.accenture.academico.g3bank.service.ContaService;
import com.accenture.academico.g3bank.util.ValorOperacao;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(path = "/g3bank")
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	 @GetMapping(value = "/contas")
	 @ApiOperation(value="Retorna uma lista de contas")
	    public  ResponseEntity <List<Conta>> Get() {
		 List<Conta> contas = contaService.searchAll();
	        return ResponseEntity.ok().body(contas);
	    }
	 
	 @GetMapping(value = "/conta/{id}")
	 @ApiOperation(value="Retorna uma conta única")
	    public ResponseEntity<Conta> GetById(@PathVariable(value = "id") Long id)
	    {
			 Conta conta = contaService.search(id);
		       
		       if (conta == null) {
					return ResponseEntity.notFound().build();
				}
		       else {
		    	   return ResponseEntity.ok().body(conta);
		       }
	    }
	 
	 @RequestMapping(value = "/conta/{id}", method =  RequestMethod.PUT)
	 @ApiOperation(value="Atualiza agência de uma conta")
	    public ResponseEntity<Conta> Put(@PathVariable(value = "id") Long id, @Valid @RequestBody Conta newConta)
	    {
	            Conta conta = contaService.update(id, newConta);
		        return ResponseEntity.ok().body(conta);
	    }
	 
	 @RequestMapping(value = "/conta", method =  RequestMethod.POST)
	 @ApiOperation(value="Salva uma conta")
	    public  ResponseEntity<Conta> Post(@Valid @RequestBody Conta conta)
	    {
			 Conta newConta = contaService.save(conta);
			 return ResponseEntity.ok().body(newConta);
	    }
	    
	   @DeleteMapping(value = "/conta/{id}")
	   @ApiOperation(value="Deleta uma conta")
	    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Long id)
	    {
		   contaService.delete(id);
		   return ResponseEntity.noContent().build();
	    }
	   
	   	@RequestMapping(value ="/sacar/{id}", method =  RequestMethod.PUT)
	   	@ApiOperation(value="Faz operação de saque")
		public ResponseEntity<Void> Withdraw(@PathVariable(value = "id") Long id, @Valid @RequestBody ValorOperacao valor) {
			contaService.withdraw(id, valor);
			return ResponseEntity.noContent().build();
		}
	   	
	   	@RequestMapping(value = "/depositar/{id}", method =  RequestMethod.PUT)
	   	@ApiOperation(value="Faz operação de depósito")
		public ResponseEntity<Void> Deposit(@PathVariable(value = "id") Long id,  @Valid @RequestBody ValorOperacao valor) {
			contaService.deposit(id, valor);
			return ResponseEntity.noContent().build();
		}
		
	   	@RequestMapping(value = "/transferir/{idOrigem}/{idDestino}", method =  RequestMethod.PUT)
	   	@ApiOperation(value="Faz operação de transfêrencia")
		public ResponseEntity<Void> Transfer(@PathVariable(value = "idOrigem") Long idContaOrigem, @PathVariable(value = "idDestino") Long idContaDestino,  @Valid @RequestBody ValorOperacao valor ) {
			contaService.transfer(idContaOrigem, idContaDestino, valor);
			return ResponseEntity.noContent().build();
		}
	    
}

