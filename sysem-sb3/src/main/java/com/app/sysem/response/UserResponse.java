package com.app.sysem.response;

import java.util.List;

import com.app.sysem.model.User;

import lombok.Data;

@Data
public class UserResponse {
	
	private List<User> users;

}
