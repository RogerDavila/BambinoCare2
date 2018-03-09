package com.bambinocare.api.bambinocareAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id", nullable = false, unique = true)
	private Integer eventId;

	@JoinColumn(name = "event_type_id", nullable = false)
	@OneToOne(fetch = FetchType.EAGER)
	private EventType eventType;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "neighborhood", nullable = false)
	private String neighborhood;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "bambinos_qty", nullable = false)
	private Integer bambinosQty;

	@Column(name = "age", nullable = false)
	private String age;

	@Column(name = "comments", nullable = true)
	private String comments;

	public Event(Integer eventId, EventType eventType, String street, String neighborhood, String city,
			String state, Integer bambinosQty, String age, String comments) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
		this.street = street;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
		this.bambinosQty = bambinosQty;
		this.age = age;
		this.comments = comments;
	}

	public Event() {
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getBambinosQty() {
		return bambinosQty;
	}

	public void setBambinosQty(Integer bambinosQty) {
		this.bambinosQty = bambinosQty;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}