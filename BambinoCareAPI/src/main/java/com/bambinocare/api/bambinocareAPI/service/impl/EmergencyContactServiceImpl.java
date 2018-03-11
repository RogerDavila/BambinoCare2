package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Client;
import com.bambinocare.api.bambinocareAPI.model.EmergencyContact;
import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.repository.EmergencyContactRepository;
import com.bambinocare.api.bambinocareAPI.service.EmergencyContactService;

@Service
public class EmergencyContactServiceImpl implements EmergencyContactService {

	@Autowired
	private EmergencyContactRepository emergencyContactRepository;
	
	@Override
	public List<EmergencyContact> findByClient(Client client) {
		
		if(client == null) return new ArrayList<EmergencyContact>();
		
		return emergencyContactRepository.findByClient(client);
	}
	
	@Override
	public List<EmergencyContact> findByUser(User user) {
		if(user == null) return new ArrayList<>();
		return emergencyContactRepository.findByClientUser(user);
	}

	@Override
	public EmergencyContact createContact(EmergencyContact contact) {
		if(contact == null) return null;
		return emergencyContactRepository.save(contact);
	}
	
	@Override
	public EmergencyContact findByContactIdAndUser(Integer contactId, User user) {
		if(user == null) return null;
		return emergencyContactRepository.findByContactIdAndClientUser(contactId, user).orElse(null);
	}
	
	@Override
	public boolean removeContact(Integer contactId){
		if(emergencyContactExists(contactId)) return false;
		emergencyContactRepository.delete(findByContactId(contactId));
		
		return true;
	}

	@Override
	public EmergencyContact findByContactId(Integer contactId) {
		return emergencyContactRepository.findById(contactId).orElse(null);
	}
	
	@Override
	public boolean emergencyContactExists(Integer contactId) {
		
		if(emergencyContactRepository.existsById(contactId)) {
			return true;
		}
		
		return false;
	}

}
