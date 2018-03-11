package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.BookingType;

public interface BookingTypeService {

	List<BookingType> findAll();
	
	BookingType findByBookingTypeId(Integer id);
	
}
