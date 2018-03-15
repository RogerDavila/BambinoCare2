package com.bambinocare.api.bambinocareAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking_type")
public class BookingType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_type_id", unique = true, nullable = false)
	private Integer bookingTypeId;

	@Column(name = "booking_type_desc", nullable = false)
	private String bookingTypeDesc;
	
	public BookingType(Integer bookingTypeId, String bookingTypeDesc) {
		super();
		this.bookingTypeId = bookingTypeId;
		this.bookingTypeDesc = bookingTypeDesc;
	}

	public BookingType() {
	}

	public Integer getBookingTypeId() {
		return bookingTypeId;
	}

	public void setBookingTypeId(Integer bookingTypeId) {
		this.bookingTypeId = bookingTypeId;
	}

	public String getBookingTypeDesc() {
		return bookingTypeDesc;
	}

	public void setBookingTypeDesc(String bookingTypeDesc) {
		this.bookingTypeDesc = bookingTypeDesc;
	}
}