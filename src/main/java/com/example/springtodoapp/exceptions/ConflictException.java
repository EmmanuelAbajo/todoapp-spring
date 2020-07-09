package com.example.springtodoapp.exceptions;

public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = -2360567745576548386L;
	
	public ConflictException(Long id) {
		super(id.toString() + " already exists");
	}

}
