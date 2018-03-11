package com.bambinocare.api.bambinocareAPI.service;

import java.util.List;

import com.bambinocare.api.bambinocareAPI.model.Role;

public interface RoleService {

	List<Role> findAll();
	
	Role findByRoleDesc(String roleDesc);
	
}
