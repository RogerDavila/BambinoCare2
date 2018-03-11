package com.bambinocare.api.bambinocareAPI.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Event;
import com.bambinocare.api.bambinocareAPI.repository.EventRepository;
import com.bambinocare.api.bambinocareAPI.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public Event createEvent(Event event) {
		if(event == null) return null;
		return eventRepository.save(event);
	}
	
}
