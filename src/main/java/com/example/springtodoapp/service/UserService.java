package com.example.springtodoapp.service;

import java.util.List;
import java.util.Optional;

import com.example.springtodoapp.entity.User;

public interface UserService {
	
	User getLoggedInUser();

	Optional<User> signin(String username, String password, String email);
	
	Optional<User> createAdmin(String username, String password, String email);

	List<User> getAll();

}
