package com.app.sysem.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.sysem.model.ChangePasswordRequest;
import com.app.sysem.response.UserResponseRest;
import com.app.sysem.services.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/app/users")
@RequiredArgsConstructor
public class UserController {

	private final UserServiceImpl service;

	@PatchMapping
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
		service.changePassword(request, connectedUser);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * search by name
	 * 
	 * @param name
	 * @return
	 */
	@GetMapping("/filter/{email}")
	public ResponseEntity<UserResponseRest> searchByEmail(@PathVariable String email) {
		ResponseEntity<UserResponseRest> response = service.searchByEmail(email); 
		return response;
	}

}