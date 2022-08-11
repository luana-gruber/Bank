package com.accenture.academico.g3bank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.academico.g3bank.entity.Agencia;
import com.accenture.academico.g3bank.entity.Cliente;
import com.accenture.academico.g3bank.service.ClienteService;

import io.swagger.annotations.ApiOperation;

@Controller
@RestController
@RequestMapping
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	@ApiOperation(value="Retorna uma lista de clientes")
	    public ResponseEntity<List<Cliente>> Get() {
		List<Cliente> clientes = clienteService.searchAll();
        return ResponseEntity.ok().body(clientes);
	}
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	@ApiOperation(value="Retorna uma lista de clientes")
	    public ModelAndView get() {
		List<Cliente> clientes = clienteService.searchAll();
		
		ModelAndView modelAndView = new ModelAndView("clientes");
		modelAndView.addObject("clientes", clientes);
		
		return modelAndView;
	}


	@RequestMapping(value = "/cliente/{cpf}", method = RequestMethod.GET)
	@ApiOperation(value="Retorna um cliente único")
	    public ResponseEntity<Cliente> GetById(@PathVariable String cpf)
	        
	    {
		Cliente cliente = clienteService.searchCpf(cpf);
	       
	       if (cliente == null) {
				return ResponseEntity.notFound().build();
			}
	       else {
	    	   return ResponseEntity.ok().body(cliente);
	       }
	    }
	
	 @RequestMapping(value = "/cliente", method =  RequestMethod.POST)
	 @ApiOperation(value="Salva um cliente")
	    public ResponseEntity<Cliente> Post(@Valid @RequestBody Cliente cliente)
	    {
		 Cliente newCliente = clienteService.save(cliente);
		 return ResponseEntity.ok().body(newCliente);
	        
	    }
	 
	 @RequestMapping(value="cliente/{cpf}", method = RequestMethod.PUT)
	 @ApiOperation(value="Atualiza um cliente")
		public ResponseEntity<Cliente> Put(@PathVariable String cpf, @Valid @RequestBody Cliente newCliente)
		
	 {
		  	Cliente cliente = clienteService.updateClient(cpf, newCliente);
	        return ResponseEntity.ok().body(cliente);
		  
	 }
	  
		@RequestMapping(value="cliente/{cpf}",method = RequestMethod.DELETE)
		@ApiOperation(value="Deleta um cliente")
		public ResponseEntity<Void> Delete(@PathVariable String cpf){
			
			clienteService.delete(cpf);
			return ResponseEntity.noContent().build();
			 
		}
	}

