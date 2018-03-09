package com.bambinocare.api.bambinocareAPI.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bambinocare.api.bambinocareAPI.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	@Qualifier("authenticationService")
	private AuthenticationService authService;
	
	protected LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		User user = new ObjectMapper().readValue(request.getInputStream(), User.class);

		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
				user.getPassword(), Collections.emptyList()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException{
		authService.doAuthentication(response, auth);
	}

}
