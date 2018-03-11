package com.bambinocare.api.bambinocareAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);
	
}
