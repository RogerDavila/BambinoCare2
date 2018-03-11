package com.bambinocare.api.bambinocareAPI.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="verification_token")
public class VerificationToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "token_id")
	private Long tokenId;

	@Column(name = "token")
	private String token;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "expiry_date")
	private Date expiryDate;

	public VerificationToken() {
	}

	public VerificationToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}

	public VerificationToken(Long tokenId, String token, User user, Date expiryDate) {
		super();
		this.tokenId = tokenId;
		this.token = token;
		this.user = user;
		this.expiryDate = expiryDate;
	}

	public Long getTokenId() {
		return tokenId;
	}

	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
