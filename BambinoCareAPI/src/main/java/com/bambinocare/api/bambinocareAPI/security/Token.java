package com.bambinocare.api.bambinocareAPI.security;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.bambinocare.api.bambinocareAPI.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component("token")
public class Token {
	
	final long EXPIRATION = 15*60*1000;
	final String SIGNATURE = "B4mb1n0C4r32017";
	final public String PREFIX = "Bearer";
	final public String HEADER = "Authorization";
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	
	
	public String build(String username) {
		String jwt = Jwts.builder()
				.setId(UUID.randomUUID().toString())
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(SignatureAlgorithm.HS512, SIGNATURE)
				.compact();
		return jwt;
	}
	
	public String discover(String token) {
		String username = Jwts.parser()
				.setSigningKey(SIGNATURE)
				.parseClaimsJws(token.replace(PREFIX, ""))
				.getBody()
				.getSubject();
		
		return userDetailsService.loadUserByUsername(username).getUsername();
	}

}
