package com.accenture.academico.g3bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.academico.g3bank.entity.Extrato;
import com.accenture.academico.g3bank.service.ExtratoService;

import io.swagger.annotations.ApiOperation;

public class ExtratoController {
	
	@Autowired
	private ExtratoService extratoService;
	
	@RequestMapping(value = "/extrato/{id}", method = RequestMethod.GET)
	@ApiOperation(value="Retorna um extrato único")
	public ResponseEntity<Extrato> GetById(@PathVariable(value = "id") Long id)
    {
       Extrato extrato = extratoService.search(id);
       
       if (extrato == null) {
			return ResponseEntity.notFound().build();
		}
       else {
    	   return ResponseEntity.ok().body(extrato);
       }
    }
}
