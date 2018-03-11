package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.City;
import com.bambinocare.api.bambinocareAPI.repository.CityRepository;
import com.bambinocare.api.bambinocareAPI.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}

}
