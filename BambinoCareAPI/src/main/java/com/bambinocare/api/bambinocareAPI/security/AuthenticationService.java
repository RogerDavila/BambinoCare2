package com.bambinocare.api.bambinocareAPI.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;


public interface AuthenticationService {

	void doAuthentication(HttpServletResponse response, Authentication auth);
	
	Authentication getAuthentication(HttpServletRequest request);
	
}
