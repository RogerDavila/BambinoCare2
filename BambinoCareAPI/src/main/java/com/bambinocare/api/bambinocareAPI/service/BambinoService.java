package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;
import java.util.Set;

import com.bambinocare.api.bambinocareAPI.model.Bambino;
import com.bambinocare.api.bambinocareAPI.model.Client;
import com.bambinocare.api.bambinocareAPI.model.User;

public interface BambinoService {

	List<Bambino> findByClient(Client client);
	
	Bambino createBambino(Bambino bambino);
	
	Bambino findByBambinoIdAndClientUser (Integer bambinoId, User user);
	
	boolean removeBambino(Integer bambinoId);
	
	Bambino findByBambinoId(Integer bambinoId);
	
	List<Bambino> findByClientUser(User user);
	
	Set<Bambino> findByBambinoIdAndUser(List<String> bambinoIds, User user);
	
}
