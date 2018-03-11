package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.BookingStatus;
import com.bambinocare.api.bambinocareAPI.repository.BookingStatusRepository;
import com.bambinocare.api.bambinocareAPI.service.BookingStatusService;

@Service
public class BookingStatusServiceImpl implements BookingStatusService {

	@Autowired
	private BookingStatusRepository bookingStatusRepository;
	
	@Override
	public List<BookingStatus> findAll() {
		return bookingStatusRepository.findAll();
	}

	@Override
	public BookingStatus findByBookingStatusDesc(String bookingStatusDesc) {
		return bookingStatusRepository.findByBookingStatusDesc(bookingStatusDesc).orElse(null);
	}

}
