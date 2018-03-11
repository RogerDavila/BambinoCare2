package com.bambinocare.api.bambinocareAPI.service;

import com.bambinocare.api.bambinocareAPI.model.Client;
import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.model.Validation;

public interface ClientService {

	Client createClient(Client client);
	
	Client findByUser(User user);
	
	Client findByUserEmail(String email);
	
	Client saveClient(Client client);
	
	Validation validateClientForm(Client client, User user);
	
	boolean clientExists( Client client);
	
}
