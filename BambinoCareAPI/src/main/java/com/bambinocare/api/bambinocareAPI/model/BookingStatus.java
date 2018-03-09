package com.bambinocare.api.bambinocareAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking_status")
public class BookingStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_status_id", unique = true, nullable = false)
	private Integer bookingStatusId;

	@Column(name = "booking_status_desc", nullable = false)
	private String bookingStatusDesc;

	public BookingStatus(Integer bookingStatusId, String bookingStatusDesc) {
		super();
		this.bookingStatusId = bookingStatusId;
		this.bookingStatusDesc = bookingStatusDesc;
	}

	public BookingStatus() {
	}

	public Integer getBookingStatusId() {
		return bookingStatusId;
	}

	public void setBookingStatusId(Integer bookingStatusId) {
		this.bookingStatusId = bookingStatusId;
	}

	public String getBookingStatusDesc() {
		return bookingStatusDesc;
	}

	public void setBookingStatusDesc(String bookingStatusDesc) {
		this.bookingStatusDesc = bookingStatusDesc;
	}

}