package com.example.springtodoapp.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
		if (authHeader == null || !authHeader.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}

	// Token validation
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		if (token != null) {
			// parse the token.
			DecodedJWT parsedToken = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes())).build()
					.verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
			String user = parsedToken.getSubject();
			List<GrantedAuthority> roles = parsedToken.getClaim("roles").asList(String.class)
					.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, roles);
			}

			return null;
		}
		return null;
	}

}
