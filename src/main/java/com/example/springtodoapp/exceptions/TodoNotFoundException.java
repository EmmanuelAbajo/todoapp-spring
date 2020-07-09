package com.example.springtodoapp.exceptions;

import com.example.springtodoapp.entity.Todo;

public class TodoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4947891395926730588L;
	
	public TodoNotFoundException(Class<Todo> todo, Long id) {
		super("Todo with Id " + id.toString() + " not found");
	}

}
