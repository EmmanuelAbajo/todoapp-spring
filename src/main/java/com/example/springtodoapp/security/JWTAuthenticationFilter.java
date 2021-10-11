package com.example.springtodoapp.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;

import com.example.springtodoapp.dto.LoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AuthenticationManager authManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
		setFilterProcessesUrl(SecurityConstants.LOGIN_URL); 
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		logger.info("Attempting User Authentication");
		try {
			LoginDTO cred = new ObjectMapper().readValue(request.getInputStream(),LoginDTO.class);
			return authManager.authenticate(new UsernamePasswordAuthenticationToken(
					cred.getUsername(),
					cred.getPassword(),
					new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		logger.info("Creating JWT for Logged in User");
		Collection<GrantedAuthority> roles = ((User) authResult.getPrincipal()).getAuthorities();
		String token = JWT.create()
				.withIssuer("Emmanuel Abajo")
                .withSubject(((User) authResult.getPrincipal()).getUsername())
                .withClaim("roles", roles.stream().map(role -> role.getAuthority())
                		.filter(Objects::nonNull).collect(Collectors.toList()))
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	}
	
	
	
	

}
