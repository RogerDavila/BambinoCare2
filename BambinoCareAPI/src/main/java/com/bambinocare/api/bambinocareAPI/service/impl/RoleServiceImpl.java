package com.bambinocare.api.bambinocareAPI.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bambinocare.api.bambinocareAPI.model.Role;
import com.bambinocare.api.bambinocareAPI.repository.RoleRepository;
import com.bambinocare.api.bambinocareAPI.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role findByRoleDesc(String roleDesc) {
		return roleRepository.findByRoleDesc(roleDesc).orElse(null);
	}

}
