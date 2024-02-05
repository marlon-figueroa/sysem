package com.app.sysem.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.sysem.dao.UserRepository;
import com.app.sysem.model.ChangePasswordRequest;
import com.app.sysem.model.User;
import com.app.sysem.response.UserResponseRest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository repository;

	public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

		var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

		// check if the current password is correct
		if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
			throw new IllegalStateException("Wrong password");
		}
		// check if the two new passwords are the same
		if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
			throw new IllegalStateException("Password are not the same");
		}

		// update the password
		user.setPassword(passwordEncoder.encode(request.getNewPassword()));

		// save the new password
		repository.save(user);
	}

	@Override
	public ResponseEntity<UserResponseRest> search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserResponseRest> searchById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserResponseRest> save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserResponseRest> update(User user, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<UserResponseRest> deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<UserResponseRest> searchByEmail(String email) {
		UserResponseRest response = new UserResponseRest();
		List<User> list = new ArrayList<>();

		try {

			// search producto by name
			Optional<User> op = repository.findByEmail(email);
			
			if(op.isPresent()) {
				list.add(op.get());				
				response.getUserResponse().setUsers(list);
				response.setMetadata("Respuesta ok", "00", "Usuarios encontrados");
				
			} else {
				response.setMetadata("respuesta nok", "-1", "Usuarios no encontrados ");
				return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			e.getStackTrace();
			response.setMetadata("respuesta nok", "-1", "Error al buscar usuario por nombre");
			return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
	}
	
	
}