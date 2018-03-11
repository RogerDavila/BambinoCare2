package com.bambinocare.api.bambinocareAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
