package com.project.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.security.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
