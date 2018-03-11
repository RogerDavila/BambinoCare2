package com.bambinocare.api.bambinocareAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.BookingType;
import com.bambinocare.api.bambinocareAPI.model.Cost;

@Repository
public interface CostRepository extends JpaRepository<Cost, Integer> {
	
	List<Cost> findByBambinoQuantityAndBookingTypeOrderByHourQuantity(Integer bambinoQuantity, BookingType bookingType);

	List<Cost> findAllByOrderByHourQuantity();
	
	List<Cost> findByBookingTypeOrderByHourQuantity(BookingType bookingType);
	
}
