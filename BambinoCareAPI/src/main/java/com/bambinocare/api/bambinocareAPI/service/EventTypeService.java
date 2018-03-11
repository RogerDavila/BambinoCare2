package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.EventType;

public interface EventTypeService {

	List<EventType> findAll();
	
	EventType findByEventTypeId(Integer eventTypeId);
	
}
