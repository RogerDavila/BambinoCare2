package com.bambinocare.api.bambinocareAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bambinocare.api.bambinocareAPI.model.Bambinaia;
import com.bambinocare.api.bambinocareAPI.model.Booking;
import com.bambinocare.api.bambinocareAPI.model.BookingStatus;
import com.bambinocare.api.bambinocareAPI.service.BookingService;
import com.bambinocare.api.bambinocareAPI.service.BookingStatusService;
import com.bambinocare.api.bambinocareAPI.service.EmailService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private BookingStatusService bookingStatusService;

	@Autowired
	private EmailService emailService;

	@GetMapping
	public ResponseEntity<List<Booking>> getAllBookings() {
		List<Booking> bookings = null;
		try {
			bookings = bookingService.findAll();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}

		if (bookings.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(bookings);
	}

	@GetMapping(value = "/{bookingId}")
	public ResponseEntity<Booking> getBookingById(@PathVariable Integer bookingId) {
		Booking booking = null;

		try {
			booking = bookingService.findByBookingId(bookingId);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}

		if (booking == null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(booking);
	}

	@PutMapping("/cancel")
	public ResponseEntity<?> cancelBooking(@RequestBody Integer bookingId) {
		Booking booking = bookingService.findByBookingIdAndBookingStatusBookingStatusDescNotIn(bookingId, "Cancelada");

		if (booking == null) {
			return ResponseEntity.badRequest().header("result", "No se puede cancelar la reservación solicitada")
					.build();
		}

		BookingStatus bookingStatus = bookingStatusService.findByBookingStatusDesc("Cancelada");

		if (bookingStatus == null) {
			return ResponseEntity.badRequest().header("result", "No se permiten cancelaciones de reservación").build();
		}

		booking.setBookingStatus(bookingStatus);
		Booking bookingEdited = bookingService.editBooking(booking);

		if (bookingEdited == null) {
			return ResponseEntity.badRequest()
					.header("result", "No se pudó cancelar la reservación. Intente nuevamente.").build();
		}

		emailService.sendSimpleMessage(booking.getClient().getUser().getEmail(), "rogerdavila.stech@gmail.com",
				"Reservación Cancelada",
				"Su reservación del día del día " + booking.getDate()
						+ "  ha sido cancelada. Puedes revisar el detalle en"
						+ " la siguiente liga: \n\r \n\r www.bambinocare.com.mx");

		return ResponseEntity.ok().header("result", "La reservación ha sido cancelada.").build();
	}
	
	@PutMapping("/approve")
	public ResponseEntity<?> approveBooking (@RequestParam(name = "bookingId", required = false) Integer bookingId, @RequestBody Bambinaia bambinaia){
		Booking booking = bookingService.findByBookingIdAndBookingStatusBookingStatusDescNotIn(bookingId,
				"Cancelada", "Agendada", "Rechazada");
		
		if(booking == null) {
			return ResponseEntity.badRequest().header("result", "No se puede agendar la reservación solicitada").build();
		}
		
		return ResponseEntity.ok().header("result", "La reservación ha sido agendada").build();
	}
}
