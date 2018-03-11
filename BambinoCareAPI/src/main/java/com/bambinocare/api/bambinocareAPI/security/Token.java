package com.bambinocare.api.bambinocareAPI.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class Token {

	final long EXPIRATION = 15 * 60 * 1000;
	final String SIGNATURE = "B4mb1n0C4r32017";
	final public String PREFIX = "Bearer";
	final public String HEADER = "Authorization";
	final public String AUTHORITIES_KEY = "Authorities";

	@Autowired
	private UserDetailsService userDetailsService;

	// Construye nuestro JWT, pasandole el email como subject, un tiempo de expiración
	// una firma, un ID. Y se le pasa el rol en un claim 
	public String build(String email) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException,
			SignatureException, IllegalArgumentException {

		//String rol = userService.findByEmail(email).getRole().getRoleDesc();
		String rol = "";
		String jwt = Jwts.builder()
						.setId(UUID.randomUUID().toString())
						.setSubject(email)
						.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
						.signWith(SignatureAlgorithm.HS512, SIGNATURE)
						.claim(AUTHORITIES_KEY, rol)
						.compact();
		return jwt;
	}

	// Obtiene nuestro UserDetails a partir del JWT
	public UserDetails discover(String token) throws ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException {
		
		String email = Jwts.parser()
						.setSigningKey(SIGNATURE)
						.parseClaimsJws(token.replace(PREFIX, ""))
						.getBody()
						.getSubject();

		return userDetailsService.loadUserByUsername(email);
	}

	// Esta es una opción para recoger los Authorities directamente del JWT,
	// sin embargo se optó por recoger las Authorities desde la BD y agregarlas
	// al UsernamePasswordAuthenticationToken
	public Collection<? extends GrantedAuthority> getAuthorities(String token) throws ExpiredJwtException,
			UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		
		Claims claims = Jwts.parser()
							.setSigningKey(SIGNATURE)
							.parseClaimsJws(token.replace(PREFIX, ""))
							.getBody();

		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(claims.get(AUTHORITIES_KEY).toString().split(",")).stream()
				.map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

		return authorities;
	}

}
