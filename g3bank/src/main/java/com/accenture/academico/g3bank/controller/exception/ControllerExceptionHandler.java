package com.accenture.academico.g3bank.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.accenture.academico.g3bank.exceptions.BusinessRuleException;
import com.accenture.academico.g3bank.exceptions.EmptyFieldsException;
import com.accenture.academico.g3bank.exceptions.EntityNotFoundException;
import com.accenture.academico.g3bank.exceptions.IntegratyViolationException;
import com.accenture.academico.g3bank.exceptions.InvalidFieldException;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(EntityNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(IntegratyViolationException.class)
	public ResponseEntity<StandardError> dataIntegrity(IntegratyViolationException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Integridade de dados", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	@ExceptionHandler(EmptyFieldsException.class)
	public ResponseEntity<StandardError> dataIntegrity(EmptyFieldsException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Campo vazio", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(InvalidFieldException.class)
	public ResponseEntity<StandardError> dataIntegrity(InvalidFieldException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Campo inválido", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(BusinessRuleException.class)
	public ResponseEntity<StandardError> dataIntegrity(BusinessRuleException e, HttpServletRequest request) {

		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Regra de negócio", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

}
