package com.example.springtodoapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.example.springtodoapp.dto.SignUpDTO;
import com.example.springtodoapp.entity.User;
import com.example.springtodoapp.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(value = "/signup", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public User signUp(@RequestBody @Valid SignUpDTO signUpDto) {
		return userService.signup(signUpDto.getUsername(), signUpDto.getPassword(), signUpDto.getEmail())
				.orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User already exists!"));
	}
	
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAll();
	}

}
