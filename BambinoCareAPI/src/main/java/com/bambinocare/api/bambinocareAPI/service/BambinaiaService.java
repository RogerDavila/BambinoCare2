package com.bambinocare.api.bambinocareAPI.service;

import java.util.Date;
import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.Bambinaia;

public interface BambinaiaService {

	List<Bambinaia> findAll();
	
	Bambinaia findByBambinaiaId(Integer bambinaiaId);
	
	Bambinaia createBambinaia(Bambinaia bambinaia);
	
	Bambinaia editBambinaia(Bambinaia bambinaia);
	
	boolean removeBambinaia(Integer bambinaiaId);
	
	Bambinaia findByUserEmail(String email);
	
	boolean isBambinaiaAvailable(Date startDate, Date finalDate, Bambinaia bambinaia);
	
	boolean bambinaiaExists( Bambinaia bambinaia );
	
}
