package com.bambinocare.api.bambinocareAPI.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private AuthenticationService authenticationService;

	/*@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication auth = null;
		// Delega la autenticación
		try {
			auth = authenticationService.getAuthentication((HttpServletRequest)request);
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
		} catch (MalformedJwtException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		// Aplicar autenticación a SecurityContextHolder
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		// continuar con la petición
		chain.doFilter(request, response);
		
		// Limpia el contexto de la autenticación
		SecurityContextHolder.getContext().setAuthentication(null);
	}*/

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Authentication auth = null;
		// Delega la autenticación
		try {
			auth = authenticationService.getAuthentication((HttpServletRequest)request);
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
		} catch (UnsupportedJwtException e) {
			e.printStackTrace();
		} catch (MalformedJwtException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		// Aplicar autenticación a SecurityContextHolder
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		// continuar con la petición
		filterChain.doFilter(request, response);
		
		// Limpia el contexto de la autenticación
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	
	
}
