package com.bambinocare.api.bambinocareAPI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationErrorComponent authenticationErrorComponent;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationFilter authenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(authenticationErrorComponent).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
				.antMatchers("/css/*", "/img/*", "/fonts/*","/js/*").permitAll()
				.antMatchers("/signup/**","/login","/","/loginsuccess","/recoverypassword","/mail/**", "/logout").permitAll()
				.antMatchers("/users/**").hasAuthority("Cliente")
				.antMatchers("/bambinaias/**").hasAuthority("Bambinaia")
				.antMatchers("/admin/**").hasAuthority("Administrador")
				.antMatchers("/payments/**").hasAuthority("Cliente")
				.anyRequest().authenticated().and()
				.addFilterBefore(login(), UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class).headers()
				.cacheControl();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService)
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
	}

	@Bean
	protected LoginFilter login() throws Exception {
		return new LoginFilter("/login", authenticationManager());
	}
}
