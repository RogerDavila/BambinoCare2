package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.Neighborhood;

public interface NeighborhoodService {

	List<Neighborhood> findAll();
	
	List<Neighborhood> findByCityCityDesc(String cityDesc);
	
}
