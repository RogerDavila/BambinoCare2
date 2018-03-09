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
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	@Column(name="password", nullable=false, length=200)
	private String password;
	
	@Transient
	private String passwordConfirm;
	
	@JoinColumn(name="role_id", nullable=false)
	@OneToOne(fetch = FetchType.EAGER)
	private Role role;
	
	@Column(name="enabled", nullable=false)
	private boolean enabled = true;

	public User() {
	}

	public User(Integer userId, String email, String password, String passwordConfirm, Role role,
			boolean enabled) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.role = role;
		this.enabled = enabled;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
}
