package com.example.springtodoapp.exceptions;

import com.example.springtodoapp.entity.User;


public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7279534158738689274L;

	public UserNotFoundException(Class<User> user, Long id) {
		super("User with Id " + id.toString() + " not found");
	}
	
	
	
	

}
