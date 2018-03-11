package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.Parameter;

public interface ParameterService {

	Parameter findByParameterKey(String key);
	
	List<Parameter> findAll();
	
	Parameter findByParameterId(Integer parameterId);
	
	Parameter createParameter(Parameter parameter);
	
	Parameter editParameter(Parameter parameter);
	
	boolean parameterExists(Parameter parameter);
	
}
