package com.bambinocare.api.bambinocareAPI.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Tutory;
import com.bambinocare.api.bambinocareAPI.repository.TutoryRepository;
import com.bambinocare.api.bambinocareAPI.service.TutoryService;

@Service
public class TutoryServiceImpl implements TutoryService {

	@Autowired
	private TutoryRepository tutoryRepository;
	
	@Override
	public Tutory createTutory(Tutory tutory) {
		if(tutory == null) return null;
		return tutoryRepository.save(tutory);
	}

}
