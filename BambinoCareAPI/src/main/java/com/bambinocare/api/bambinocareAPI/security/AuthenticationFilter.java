package com.bambinocare.api.bambinocareAPI.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component("authenticationFilter")
public class AuthenticationFilter extends GenericFilterBean{

	@Autowired
	@Qualifier("authenticationService")
	private AuthenticationService authenticationService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		Authentication auth = authenticationService.getAuthentication((HttpServletRequest)request);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		chain.doFilter(request, response);
		
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	
	
}
