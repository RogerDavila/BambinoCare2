package com.bambinocare.api.bambinocareAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_type")
public class EventType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_type_id", unique = true, nullable = false)
	private Integer eventTypeId;

	@Column(name = "event_type_desc", nullable = false)
	private String eventTypeDesc;

	public EventType(Integer eventTypeId, String eventTypeDesc) {
		super();
		this.eventTypeId = eventTypeId;
		this.eventTypeDesc = eventTypeDesc;
	}

	public EventType() {
	}

	public Integer getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventTypeDesc() {
		return eventTypeDesc;
	}

	public void setEventTypeDesc(String eventTypeDesc) {
		this.eventTypeDesc = eventTypeDesc;
	}

}