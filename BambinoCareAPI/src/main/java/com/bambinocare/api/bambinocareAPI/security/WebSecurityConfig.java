package com.bambinocare.api.bambinocareAPI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	@Qualifier("authenticationErrorComponent")
	private AuthenticationErrorComponent authenticationErrorComponent;
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(authenticationErrorComponent).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/health").permitAll()
				.antMatchers(HttpMethod.POST,"/login").permitAll()
				.anyRequest().authenticated().and()
				.addFilterBefore(new LoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.headers().cacheControl();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
