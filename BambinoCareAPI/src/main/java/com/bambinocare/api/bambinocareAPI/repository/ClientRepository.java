package com.bambinocare.api.bambinocareAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.Client;
import com.bambinocare.api.bambinocareAPI.model.User;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

	Optional<Client> findByUser(User user);
	
	Optional<Client> findByUserEmail(String email);
	
}
