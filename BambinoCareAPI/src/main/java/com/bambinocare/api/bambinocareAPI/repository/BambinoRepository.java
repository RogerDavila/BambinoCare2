package com.bambinocare.api.bambinocareAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.Bambino;
import com.bambinocare.api.bambinocareAPI.model.Client;
import com.bambinocare.api.bambinocareAPI.model.User;

@Repository
public interface BambinoRepository extends JpaRepository<Bambino, Integer> {
	
	List<Bambino> findByClient(Client client);
	
	List<Bambino> findByClientUser(User user);
	
	Optional<Bambino> findByBambinoIdAndClientUser(Integer bambinoId, User user);
	
}
