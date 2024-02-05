package com.app.sysem.services;

import org.springframework.http.ResponseEntity;

import com.app.sysem.model.User;
import com.app.sysem.response.UserResponseRest;

public interface IUserService {
	
	public ResponseEntity<UserResponseRest> search();

	public ResponseEntity<UserResponseRest> searchById(Long id);

	public ResponseEntity<UserResponseRest> searchByEmail(String email);
	
	public ResponseEntity<UserResponseRest> save(User user);

	public ResponseEntity<UserResponseRest> update(User user, Long id);

	public ResponseEntity<UserResponseRest> deleteById(Long id);

}
