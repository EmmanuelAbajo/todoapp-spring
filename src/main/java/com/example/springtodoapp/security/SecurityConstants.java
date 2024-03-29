package com.example.springtodoapp.security;

public class SecurityConstants {
	
	public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/v1/user/signup";
    public static final String LOGIN_URL = "/api/v1/user/login";
    public static final String KEY_URL = "/api/v1/todo/key";

}
