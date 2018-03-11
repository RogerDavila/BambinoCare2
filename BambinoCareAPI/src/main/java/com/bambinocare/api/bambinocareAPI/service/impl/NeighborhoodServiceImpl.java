package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Neighborhood;
import com.bambinocare.api.bambinocareAPI.repository.NeighborhoodRepository;
import com.bambinocare.api.bambinocareAPI.service.NeighborhoodService;

@Service
public class NeighborhoodServiceImpl implements NeighborhoodService {

	@Autowired
	private NeighborhoodRepository neighborhoodRepository;
	
	@Override
	public List<Neighborhood> findAll() {
		return neighborhoodRepository.findAll();
	}

	@Override
	public List<Neighborhood> findByCityCityDesc(String cityDesc) {
		
		if(cityDesc == null || cityDesc.equalsIgnoreCase("")) {
			return null;
		}
		
		return neighborhoodRepository.findByCityCityDesc(cityDesc);
	}
	
}
