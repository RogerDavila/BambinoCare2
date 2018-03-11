package com.bambinocare.api.bambinocareAPI.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bambinocare.api.bambinocareAPI.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByRoleDesc( String roleDesc );
}
