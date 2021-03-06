package com.bambinocare.api.bambinocareAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Integer> {

	Optional<Parameter> findByParameterKey(String key);
	
}
