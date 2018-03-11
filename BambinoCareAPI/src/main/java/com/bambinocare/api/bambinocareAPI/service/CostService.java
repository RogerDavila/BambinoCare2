package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.Booking;
import com.bambinocare.api.bambinocareAPI.model.BookingType;
import com.bambinocare.api.bambinocareAPI.model.Cost;

public interface CostService {

	List<Cost> findByBambinoQuantityAndBookingTypeOrderByHourQuantity(Integer bambinoQuantity,
			BookingType bookingType);

	List<Cost> findAllByOrderByHourQuantity();

	Double calculateTotalCost(Double duration, Integer bambinoQuantity, BookingType bookingType);

	Double calculateTotalCostByBooking(Booking booking);

	Double calculateTotalCostBambinoEvents(Double duration, Integer bambinoQuantity, BookingType bookingType);

	List<Cost> findByBookingTypeOrderByHourQuantity(BookingType bookingType);
	
}
