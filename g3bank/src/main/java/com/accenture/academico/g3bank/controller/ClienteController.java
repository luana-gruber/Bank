package com.accenture.academico.g3bank.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.academico.g3bank.entity.Cliente;
import com.accenture.academico.g3bank.repository.ClienteRepository;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping(path = "/g3bank")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	@ApiOperation(value="Retorna uma lista de clientes")
	    public List<Cliente> Get() {
	        return clienteRepository.findAll();
	}


	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	@ApiOperation(value="Retorna um cliente único")
	    public ResponseEntity<Cliente> GetById(@PathVariable(value = "id") Integer id)
	        
	    {
		 Optional<Cliente> cliente = clienteRepository.findById(id);
	        if(cliente.isPresent())
	            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        
	    }
	
	 @RequestMapping(value = "/cliente", method =  RequestMethod.POST)
	 @ApiOperation(value="Salva um cliente")
	    public Cliente Post(@Valid @RequestBody Cliente cliente)
	    {
	        return clienteRepository.save(cliente);
	        
	    }
	 
	 @RequestMapping(value="/{id}", method = RequestMethod.PUT)
	 @ApiOperation(value="Atualiza um cliente")
		public ResponseEntity<Cliente> Put(@PathVariable(value = "id") Integer id, @Valid @RequestBody Cliente novoCliente)
		
	 {
		 
		  Optional<Cliente> contatoCliente = clienteRepository.findById(id);
		  if(contatoCliente.isPresent()) {
			  Cliente cliente = contatoCliente.get();
			  cliente.setNomeCliente(novoCliente.getNomeCliente());
			  cliente.setTelefoneCliente(novoCliente.getTelefoneCliente());
			  cliente.setEmailCliente(novoCliente.getEmailCliente());
			  
			  clienteRepository.save(cliente);
			  return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		   }
	        else
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		  
			  
		  }
	  
		@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
		@ApiOperation(value="Deleta um cliente")
		public ResponseEntity<Void> Delete(@PathVariable Integer id){

			 Optional<Cliente> cliente = clienteRepository.findById(id);
			 if(cliente.isPresent()) {
				 clienteRepository.delete(cliente.get());
			     return new ResponseEntity<>(HttpStatus.OK);
		        }
		        else
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
			 
		}
	 

