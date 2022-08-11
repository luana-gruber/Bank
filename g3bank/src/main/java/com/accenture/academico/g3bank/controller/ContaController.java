package com.accenture.academico.g3bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.academico.g3bank.entity.Conta;
import com.accenture.academico.g3bank.service.ContaService;
import com.accenture.academico.g3bank.util.ValorOperacao;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping
public class ContaController {
	
	@Autowired
	private ContaService contaService;
	
	 @GetMapping(value = "/conta")
	 @ApiOperation(value="Retorna uma lista de contas")
	    public  ResponseEntity <List<Conta>> Get() {
		 List<Conta> contas = contaService.searchAll();
	        return ResponseEntity.ok().body(contas);
	    }
	
	 @GetMapping(value = "/contas")
	 @ApiOperation(value="Retorna uma lista de contas")
	    public  ModelAndView get() {
		 List<Conta> contas = contaService.searchAll();

		 ModelAndView modelAndView = new ModelAndView("contas");
			modelAndView.addObject("contas", contas);
			
			return modelAndView;
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
		public ResponseEntity<String> Withdraw(@PathVariable(value = "id") Long id, @Valid @RequestBody ValorOperacao valor) {
			contaService.withdraw(id, valor);
			String message = "Saque realizado com sucesso!";
			return ResponseEntity.ok().body(message);
		}
	   	
	   	@RequestMapping(value = "/depositar/{id}", method =  RequestMethod.PUT)
	   	@ApiOperation(value="Faz operação de depósito")
		public ResponseEntity<String> Deposit(@PathVariable(value = "id") Long id,  @Valid @RequestBody ValorOperacao valor) {
			contaService.deposit(id, valor);
			String message = "Depósito realizado com sucesso";
			return ResponseEntity.ok().body(message);
		}
		
	   	@RequestMapping(value = "/transferir/{idOrigem}/{idDestino}", method =  RequestMethod.PUT)
	   	@ApiOperation(value="Faz operação de transfêrencia")
		public ResponseEntity<String> Transfer(@PathVariable(value = "idOrigem") Long idContaOrigem, @PathVariable(value = "idDestino") Long idContaDestino,  @Valid @RequestBody ValorOperacao valor ) {
			contaService.transfer(idContaOrigem, idContaDestino, valor);
			String message = "Transferência realizada com sucesso!";
			return ResponseEntity.ok().body(message);
		}
	    
}

