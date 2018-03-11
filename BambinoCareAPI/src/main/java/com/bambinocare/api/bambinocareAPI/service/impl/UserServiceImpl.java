package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.model.VerificationToken;
import com.bambinocare.api.bambinocareAPI.repository.UserRepository;
import com.bambinocare.api.bambinocareAPI.repository.VerificationTokenRepository;
import com.bambinocare.api.bambinocareAPI.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Override
	public List<User> listAll(){
		return userRepository.findAll();
	} 
	
	@Override
	public User findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	@Override
	public void removeUser(Integer userId){
		userRepository.delete(findByUserId(userId));
	}
	
	@Override
	public User createUser(User user){
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User newUser = userRepository.save(user);
		return newUser;
	}
	
	@Override
	public User editUser(User user){
		User newUser = userRepository.save(user);
		return newUser;
	}

	@Override
	public User findByUserId(Integer userId) {
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public boolean userExist(String email) {
		return findByEmail(email) != null ? true : false;
	}
	
	@Override
	public User updatePassword(String email, String password) {
		
		User user = findByEmail(email);
		user.setPassword(password);
		User newUser = createUser(user);
		
		return newUser;
	}
	
	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken verificationToken = new VerificationToken(token, user);
		
		verificationTokenRepository.save(verificationToken);
	}
	
	@Override
	public VerificationToken getVerificationToken(String verificationToken) {
		return verificationTokenRepository.findByToken(verificationToken);
	}

	@Override
	public Long deleteByToken(String token) {
		if(token == null || token.equals("")) {
			return null;
		}
		return verificationTokenRepository.deleteByToken(token);
	}
	
}
