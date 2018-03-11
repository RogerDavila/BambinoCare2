package com.bambinocare.api.bambinocareAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bambinocare.api.bambinocareAPI.model.Parameter;
import com.bambinocare.api.bambinocareAPI.service.ParameterService;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {

	@Autowired
	private ParameterService parameterService;
	
	@GetMapping(value="/{parameterId}")
	public ResponseEntity<Parameter> getParameterById (@PathVariable Integer parameterId){
		Parameter parameter = null;
		
		try {
			parameter = parameterService.findByParameterId(parameterId);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
		
		if(parameter == null) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(parameter);
	}
	
	@PutMapping
	public ResponseEntity<Parameter> editParameter(@RequestBody Parameter parameter){
		Parameter parameterEdited = null;
		
		try {
			parameterEdited = parameterService.editParameter(parameter);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
		
		if(parameterEdited == null) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(parameterEdited);
	}

}
