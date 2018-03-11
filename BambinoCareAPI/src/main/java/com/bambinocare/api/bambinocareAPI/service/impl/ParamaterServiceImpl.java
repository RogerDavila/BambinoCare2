package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Parameter;
import com.bambinocare.api.bambinocareAPI.repository.ParameterRepository;
import com.bambinocare.api.bambinocareAPI.service.ParameterService;

@Service
public class ParamaterServiceImpl implements ParameterService {

	@Autowired
	private ParameterRepository parameterRepository;
	
	@Override
	public Parameter findByParameterKey(String key) {
		
		if(key == null || key.equals("")) {
			return null;
		}
		
		return parameterRepository.findByParameterKey(key).orElse(null);
	}

	@Override
	public List<Parameter> findAll() {
		return parameterRepository.findAll();
	}

	@Override
	public Parameter findByParameterId(Integer parameterId) {
		return parameterRepository.findById(parameterId).orElse(null);
	}

	@Override
	public Parameter createParameter(Parameter parameter) {
		
		if(parameter == null) return null;
		
		return parameterRepository.save(parameter);
	}

	@Override
	public Parameter editParameter(Parameter parameter) {
		
		if(parameter == null || !parameterExists(parameter)) {
			return null;
		}
		
		return parameterRepository.save(parameter);
		
	}

	@Override
	public boolean parameterExists(Parameter parameter) {
		if(parameter.getParameterId() == null) {
			return false;
		}
		if(parameterRepository.existsById(parameter.getParameterId())) {
			return true;
		}
		return false;
	}

}
