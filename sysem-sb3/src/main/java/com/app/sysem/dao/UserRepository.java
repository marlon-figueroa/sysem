package com.app.sysem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.sysem.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
	
}