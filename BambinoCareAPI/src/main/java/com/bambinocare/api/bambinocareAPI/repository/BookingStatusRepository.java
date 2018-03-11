package com.bambinocare.api.bambinocareAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.BookingStatus;

@Repository
public interface BookingStatusRepository extends JpaRepository<BookingStatus, Integer>{

	Optional<BookingStatus> findByBookingStatusDesc(String bookingStatusDesc);

}
