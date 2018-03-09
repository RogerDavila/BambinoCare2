package com.bambinocare.api.bambinocareAPI.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	private final AccountStatusUserDetailsChecker accountStatusUserDetailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario: " + email + ", no encontrado");
		}

		org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, Collections.emptyList());

		accountStatusUserDetailsChecker.check(userDetails);

		return userDetails;
	}
}
