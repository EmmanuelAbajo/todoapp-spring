package com.example.springtodoapp.models;

public class ErrorBody {
	
	private final int code;
	private final String message;
	private final String error;
	
		
	public ErrorBody(int code, String message, String error) {
		this.code = code;
		this.message = message;
		this.error = error;
	}
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public String getError() {
		return error;
	}
	
	

}
