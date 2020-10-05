package com.example.springtodoapp.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.springtodoapp.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			User cred = new ObjectMapper().readValue(request.getInputStream(),User.class);
			return authManager.authenticate(new UsernamePasswordAuthenticationToken(
					cred.getUsername(),
					cred.getPassword(),
					new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	

}
