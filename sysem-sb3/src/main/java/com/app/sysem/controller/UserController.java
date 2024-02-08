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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/app/users")
@RequiredArgsConstructor
@Tag(name = "Users API")
public class UserController {

	private final UserServiceImpl service;

	@Operation(description = "Cambio de clave", summary = "Cambio de clave de usuario activo", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403") })
	@PatchMapping
	public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
		service.changePassword(request, connectedUser);
		return ResponseEntity.ok().build();
	}

	@Operation(description = "Busqueda de usuario por correo", summary = "Lista de usuarios filtrados por correo", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403") })
	@GetMapping("/filter/{email}")
	public ResponseEntity<UserResponseRest> searchByEmail(@PathVariable String email) {
		ResponseEntity<UserResponseRest> response = service.searchByEmail(email);
		return response;
	}

}