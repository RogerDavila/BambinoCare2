package com.bambinocare.api.bambinocareAPI.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bambinocare.api.bambinocareAPI.model.Bambinaia;

@RestController
@RequestMapping("/bambinaias")
public class BambinaiaController {

	@GetMapping(value="/{bambinaiaId}")
	public ResponseEntity<Bambinaia> findById(@PathVariable("bambinaiaId") Long bambinaiaId){
		System.out.println(bambinaiaId);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	public ResponseEntity<?> test (){
		System.out.println("Test");
		return ResponseEntity.ok().build();	
	} 	
}
