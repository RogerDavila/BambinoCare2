package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.BookingType;
import com.bambinocare.api.bambinocareAPI.repository.BookingTypeRepository;
import com.bambinocare.api.bambinocareAPI.service.BookingTypeService;

@Service
public class BookingTypeServiceImpl implements BookingTypeService {

	@Autowired
	private BookingTypeRepository bookingTypeRepository;
	
	@Override
	public List<BookingType> findAll() {
		return bookingTypeRepository.findAll();
	}

	@Override
	public BookingType findByBookingTypeId(Integer id) {
		return bookingTypeRepository.findById(id).orElse(null);
	}
	
}
