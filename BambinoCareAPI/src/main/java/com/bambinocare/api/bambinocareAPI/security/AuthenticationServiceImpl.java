package com.bambinocare.api.bambinocareAPI.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	@Qualifier("token")
	private Token token;
	
	public AuthenticationServiceImpl() {
	}

	@Override
	public void doAuthentication(HttpServletResponse response, Authentication auth) {
		String username = auth.getName();
		String jwt = token.build(username);
		response.addHeader(token.HEADER, token.PREFIX + " " + jwt);
	}
	
	@Override
	public Authentication getAuthentication(HttpServletRequest request) {
		String jwt = request.getHeader(token.HEADER);
		if(jwt != null && jwt.startsWith(token.PREFIX)) {
			String user = null;
			
			user = token.discover(jwt);
			
			if(user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, emptyList());
			}else {
				return null;
			}
		}
		return null;
	}
	
}
