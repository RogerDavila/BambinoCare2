package com.bambinocare.api.bambinocareAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.Bambinaia;

@Repository
public interface BambinaiaRepository extends JpaRepository<Bambinaia, Integer> {

	Optional<Bambinaia> findByUserEmail(String email);
	
}
