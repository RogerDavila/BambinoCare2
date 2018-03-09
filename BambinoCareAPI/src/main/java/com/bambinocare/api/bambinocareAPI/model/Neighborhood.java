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
@Table(name="neighborhood")
public class Neighborhood {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "neighborhood_id", unique = true, nullable = false)
	private Integer neighborhoodId;

	@Column(name = "neighborhood_desc", nullable = false)
	private String neighborhoodDesc;
	
	@JoinColumn(name="city_id", nullable=false)
	@OneToOne(fetch = FetchType.EAGER)
	private City city;

	public Neighborhood(Integer neighborhoodId, String neighborhoodDesc, City city) {
		super();
		this.neighborhoodId = neighborhoodId;
		this.neighborhoodDesc = neighborhoodDesc;
		this.city = city;
	}

	public Neighborhood() {
	}

	public Integer getNeighborhoodId() {
		return neighborhoodId;
	}

	public void setNeighborhoodId(Integer neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}

	public String getNeighborhoodDesc() {
		return neighborhoodDesc;
	}

	public void setNeighborhoodDesc(String neighborhoodDesc) {
		this.neighborhoodDesc = neighborhoodDesc;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
