package com.bambinocare.api.bambinocareAPI.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationErrorComponent implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 762265710958066324L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// Se invoca cuando el usuario intenta acceder a un controller protegido sin
		// proporcionar ninguna credencial
		// se envía una respuesta 401 (no autorizado) porque no hay una "página de
		// inicio de sesión" para redirigir
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}
