package com.bambinocare.api.bambinocareAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "state")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id", unique = true, nullable = false)
	private Integer stateId;

	@Column(name = "state_desc", nullable = false)
	private String stateDesc;

	public State() {
		super();
	}

	public State(Integer stateId, String stateDesc) {
		super();
		this.stateId = stateId;
		this.stateDesc = stateDesc;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

}