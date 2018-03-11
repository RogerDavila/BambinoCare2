package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.EventType;
import com.bambinocare.api.bambinocareAPI.repository.EventTypeRepository;
import com.bambinocare.api.bambinocareAPI.service.EventTypeService;

@Service
public class EventTypeServiceImpl implements EventTypeService{

	@Autowired
	private EventTypeRepository eventTypeRepository;
	
	@Override
	public List<EventType> findAll() {
		return eventTypeRepository.findAll();
	}

	@Override
	public EventType findByEventTypeId(Integer eventTypeId) {
		return eventTypeRepository.findById(eventTypeId).orElse(null);
	}
	
}
