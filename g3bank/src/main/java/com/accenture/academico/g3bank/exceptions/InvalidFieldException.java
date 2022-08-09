package com.accenture.academico.g3bank.exceptions;

public class InvalidFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public InvalidFieldException(String msg) {
		super(msg);
	}
}