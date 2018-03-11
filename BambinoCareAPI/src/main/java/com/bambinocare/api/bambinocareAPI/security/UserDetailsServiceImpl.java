package com.bambinocare.api.bambinocareAPI.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Role;
import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private final AccountStatusUserDetailsChecker accountStatusUserDetailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Usuario: " + email + ", no encontrado");
		}

		List<GrantedAuthority> authorities = buildAuthorities(user.getRole());

		org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, authorities);

		accountStatusUserDetailsChecker.check(userDetails);

		return userDetails;
	}

	private List<GrantedAuthority> buildAuthorities(Role rol) {
		Set<GrantedAuthority> auths = new HashSet<>();
		auths.add(new SimpleGrantedAuthority(rol.getRoleDesc()));

		return new ArrayList<GrantedAuthority>(auths);
	}
}
