package com.app.sysem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.sysem.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "select u from User u where u.email = :email")
	Optional<User> findByEmail(String email);

}