package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.BookingStatus;

public interface BookingStatusService {

	List<BookingStatus> findAll();
	
	BookingStatus findByBookingStatusDesc(String bookingStatusDesc);
	
}
