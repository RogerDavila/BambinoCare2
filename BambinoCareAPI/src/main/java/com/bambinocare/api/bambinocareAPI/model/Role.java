package com.bambinocare.api.bambinocareAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id", unique = true, nullable = false)
	private Integer roleId;

	@Column(name = "role_desc", nullable = false, length = 45)
	private String roleDesc;

	public Role(Integer roleId, String roleDesc) {
		super();
		this.roleId = roleId;
		this.roleDesc = roleDesc;
	}

	public Role() {
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}