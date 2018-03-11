package com.bambinocare.api.bambinocareAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.Client;
import com.bambinocare.api.bambinocareAPI.model.EmergencyContact;
import com.bambinocare.api.bambinocareAPI.model.User;

@Repository
public interface EmergencyContactRepository extends JpaRepository<EmergencyContact, Integer> {
	
	List<EmergencyContact> findByClient(Client client);
	
	List<EmergencyContact> findByClientUser(User user);
	
	Optional<EmergencyContact> findByContactIdAndClientUser(Integer contactId, User user);
	
}
