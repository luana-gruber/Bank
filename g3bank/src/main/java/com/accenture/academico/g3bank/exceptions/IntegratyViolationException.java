package com.accenture.academico.g3bank.exceptions;

public class IntegratyViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IntegratyViolationException(String msg) {
		super(msg);
	}
}