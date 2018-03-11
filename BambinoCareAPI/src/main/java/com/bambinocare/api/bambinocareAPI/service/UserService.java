package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.model.VerificationToken;

public interface UserService {
	
	List<User> listAll();
	
	User findByEmail(String email);
	
	User findByUserId(Integer userId);
	
	void removeUser(Integer userId);
	
	User createUser(User user);
	
	User editUser(User user);
	
	boolean userExist(String email);
	
	User updatePassword(String email, String password);
	
	void createVerificationToken(User user, String token);
	
	VerificationToken getVerificationToken(String verificationToken);
	
	Long deleteByToken(String token);
	
}