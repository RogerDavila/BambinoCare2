package com.bambinocare.api.bambinocareAPI.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer>{

	VerificationToken findByToken(String token);

	VerificationToken findByUser(User user);

	@Transactional
	Long deleteByToken(String token);
}
