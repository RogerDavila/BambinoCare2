package com.bambinocare.api.bambinocareAPI.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private Token token;

	// Cuando un usuario inicia sesión con éxito crea un token para ese usuario
	// de lo contrario arroja una excepción
	@Override
	public void doAuthentication(HttpServletResponse response, Authentication auth) throws ExpiredJwtException,
			UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		String username = auth.getName();
		String jwt = token.build(username);
		response.addHeader(token.HEADER, token.PREFIX + " " + jwt);
	}

	// Si el token no es válido la autenticación falla y la solicitud es rechazada
	// de lo contrario arroja una nueva instancia de UsernamePasswordAuthenticationToken
	@Override
	public Authentication getAuthentication(HttpServletRequest request) throws ExpiredJwtException,
			UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		String jwt = request.getHeader(token.HEADER);
		if (jwt != null && jwt.startsWith(token.PREFIX)) {
			UserDetails user = null;

			user = token.discover(jwt);
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			} else {
				return null;
			}
		}
		return null;
	}

}
