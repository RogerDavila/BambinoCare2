package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.State;
import com.bambinocare.api.bambinocareAPI.repository.StateRepository;
import com.bambinocare.api.bambinocareAPI.service.StateService;

@Service 
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;
	
	@Override
	public List<State> findAll() {
		return stateRepository.findAll();
	}
	
}
