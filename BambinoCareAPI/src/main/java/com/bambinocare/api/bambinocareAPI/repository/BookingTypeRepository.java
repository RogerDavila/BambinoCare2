package com.bambinocare.api.bambinocareAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.BookingType;

@Repository
public interface BookingTypeRepository extends JpaRepository<BookingType, Integer> {

}
