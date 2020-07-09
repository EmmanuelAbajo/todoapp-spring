package com.example.springtodoapp.exceptions;

public class InvalidRequestBodyException extends RuntimeException{

	private static final long serialVersionUID = -8406280770511904315L;
	
	public InvalidRequestBodyException(String bodyParam) {
		super(bodyParam + " in the request body is invalid");
	}

}
