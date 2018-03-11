package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.Client;
import com.bambinocare.api.bambinocareAPI.model.EmergencyContact;
import com.bambinocare.api.bambinocareAPI.model.User;

public interface EmergencyContactService {

	List<EmergencyContact> findByClient(Client client);
	
	List<EmergencyContact> findByUser(User user);
	
	EmergencyContact createContact(EmergencyContact contact);
	
	EmergencyContact findByContactIdAndUser(Integer contactId, User user);
	
	EmergencyContact findByContactId(Integer contactId);
	
	boolean removeContact(Integer contactId);
	
	boolean emergencyContactExists(Integer contactId);
	
}
