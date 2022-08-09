package com.accenture.academico.g3bank.exceptions;

public class EmptyFieldsException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EmptyFieldsException(String msg) {
		super(msg);
	}
}
