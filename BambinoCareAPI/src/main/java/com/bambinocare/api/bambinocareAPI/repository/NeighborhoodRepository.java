package com.bambinocare.api.bambinocareAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.Neighborhood;

@Repository
public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Integer> {

	List<Neighborhood> findByCityCityDesc(String cityDesc);
	
}
