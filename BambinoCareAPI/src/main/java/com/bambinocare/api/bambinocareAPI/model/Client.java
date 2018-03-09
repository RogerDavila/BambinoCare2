package com.bambinocare.api.bambinocareAPI.model;

import javax.persistence.CascadeType;
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
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id", nullable = false, unique = true)
	private Integer clientId;

	@Column(name = "firstname", nullable = false)
	private String firstname;

	@Column(name = "lastname", nullable = false)
	private String lastname;

	@Column(name = "phone", nullable = false)
	private String phone;

	@JoinColumn(name = "user_id", nullable = false)
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private User user;

	@Column(name = "job", nullable = false)
	private String job;

	@Column(name = "street", nullable = false)
	private String street;

	@Column(name = "neighborhood", nullable = false)
	private String neighborhood;

	@JoinColumn(name = "city_id", nullable = false)
	@OneToOne(fetch = FetchType.EAGER)
	private City city;

	@JoinColumn(name = "state_id", nullable = false)
	@OneToOne(fetch = FetchType.EAGER)
	private State state;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Integer clientId, User user, String job, String street, String neighborhood, City city, State state) {
		super();
		this.clientId = clientId;
		this.user = user;
		this.job = job;
		this.street = street;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", user=" + user + ", job=" + job + ", street=" + street
				+ ", neighborhood=" + neighborhood + ", city=" + city + ", state=" + state + "]";
	}

}