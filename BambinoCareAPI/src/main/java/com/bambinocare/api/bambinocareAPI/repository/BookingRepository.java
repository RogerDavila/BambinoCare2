package com.bambinocare.api.bambinocareAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.Bambinaia;
import com.bambinocare.api.bambinocareAPI.model.Booking;
import com.bambinocare.api.bambinocareAPI.model.User;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	List<Booking> findByClientUser(User user);

	Optional<Booking> findByBookingIdAndClientUser(Integer bookingId, User user);

	Optional<Booking> findByBookingIdAndClientUserAndBookingStatusBookingStatusDescNotIn(Integer bookingId, User user,
			String bookingStatusDesc);

	Optional<Booking> findByBookingIdAndBookingStatusBookingStatusDescNotIn(Integer bookingId,
			String... bookingStatusDesc);

	List<Booking> findByBambinaiaAndBookingStatusBookingStatusDesc(Bambinaia bambinaia, String bookingStatusDesc);

	Optional<Booking> findByBookingIdAndBookingStatusBookingStatusDescAndBambinaia(Integer bookingId,
			String bookingStatusDesc, Bambinaia bambinaia);

	Optional<Booking> findByBookingIdAndBookingStatusBookingStatusDesc(Integer bookingId, String bookingStatusDesc);

	void delete(Booking booking);

	List<Booking> findByBambinaia(Bambinaia bambinaia);

}
