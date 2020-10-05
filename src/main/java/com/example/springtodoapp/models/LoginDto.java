package com.example.springtodoapp.models;

import com.example.springtodoapp.entity.User;

public class LoginDto extends User {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
