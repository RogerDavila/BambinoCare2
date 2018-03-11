package com.bambinocare.api.bambinocareAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bambinocare.api.bambinocareAPI.model.Bambinaia;
import com.bambinocare.api.bambinocareAPI.service.BambinaiaService;

@RestController
@RequestMapping("/bambinaias")
public class BambinaiaController {
	
	@Autowired
	private BambinaiaService bambinaiaService;

	@GetMapping
	public ResponseEntity<List<Bambinaia>> findAll(){
		return ResponseEntity.ok(bambinaiaService.findAll());
	}
	
	@GetMapping(value="/{bambinaiaId}")
	public ResponseEntity<Bambinaia> findById(@PathVariable("bambinaiaId") Integer bambinaiaId){
		return ResponseEntity.ok(bambinaiaService.findByBambinaiaId(bambinaiaId));
	}
	
	@PostMapping
	public ResponseEntity<?> test (){
		System.out.println("Test");
		return ResponseEntity.ok().build();	
	} 	
}
