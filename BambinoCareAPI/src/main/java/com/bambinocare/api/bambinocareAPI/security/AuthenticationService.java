package com.bambinocare.api.bambinocareAPI.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


public interface AuthenticationService {

	void doAuthentication(HttpServletResponse response, Authentication auth) throws ExpiredJwtException, UnsupportedJwtException,
	MalformedJwtException, SignatureException, IllegalArgumentException;
	
	Authentication getAuthentication(HttpServletRequest request) throws ExpiredJwtException, UnsupportedJwtException,
	MalformedJwtException, SignatureException, IllegalArgumentException;
	
}
