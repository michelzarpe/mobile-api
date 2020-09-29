package com.appsdeveloperblog.app.ws.service;

import com.appsdeveloperblog.app.ws.model.dto.UserDTO;
import com.appsdeveloperblog.app.ws.model.dto.UserDetailsDTO;

public interface UserService {
	public UserDTO createUser(UserDetailsDTO userDetalisDTO);
}
