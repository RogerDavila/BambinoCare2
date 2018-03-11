package com.bambinocare.api.bambinocareAPI.service;

import java.util.Date;
import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.Bambinaia;
import com.bambinocare.api.bambinocareAPI.model.Booking;
import com.bambinocare.api.bambinocareAPI.model.User;
import com.bambinocare.api.bambinocareAPI.model.Validation;

public interface BookingService {

	List<Booking> findAll();

	Booking createBooking(Booking booking);

	List<Booking> findByClientUser(User user);

	Booking findByBookingId(Integer bookingId);

	boolean bookingExist(Booking booking);

	Booking findByBookingIdAndClientUser(Integer bookingId, User user);

	Booking findByBookingIdAndClientUserAndBookingStatusBookingStatusDescNotIn(Integer bookingId, User user,
			String bookingStatusDesc);

	Booking findByBookingIdAndBookingStatusBookingStatusDescNotIn(Integer bookingId, String... bookingStatusDesc);

	List<Booking> findByBambinaiaAndBookingStatusBookingStatusDesc(Bambinaia bambinaia, String bookingStatusDesc);

	Booking findByBookingIdAndBookingStatusBookingStatusDescAndBambinaia(Integer bookingId, String bookingStatusDesc,
			Bambinaia bambinaia);

	Booking findByBookingIdAndBookingStatusBookingStatusDesc(Integer bookingId,
			String bookingStatusDesc);

	boolean delete(Booking booking);
	
	String getFinalHour(String initialTime, Double duration);
	
	Date getDate(Date date, int days);
	
	boolean isValideDate (Date date, String hour);
	
	boolean isValideHour (String hour);
	
	Validation validateBookingForm(Booking booking, org.springframework.security.core.userdetails.User user);
	
	Date getBookingDateTime(Date bookingDate, String hour, Double duration, boolean isFinalDate);
	
	List<Booking> findByBambinaia(Bambinaia bambinaia);
	
	Booking editBooking(Booking booking);
	
}
