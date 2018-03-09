package com.bambinocare.api.bambinocareAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id", unique = true, nullable = false)
	private Integer cityId;

	@Column(name = "city_desc", nullable = false)
	private String cityDesc;

	public City() {
		super();
	}

	public City(Integer cityId, String cityDesc) {
		super();
		this.cityId = cityId;
		this.cityDesc = cityDesc;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityDesc() {
		return cityDesc;
	}

	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}
	
}