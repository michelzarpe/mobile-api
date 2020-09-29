package com.appsdeveloperblog.app.ws.service.implementation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.model.dto.UserDTO;
import com.appsdeveloperblog.app.ws.model.dto.UserDetailsDTO;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.Utils;

@Service
public class UserServIceImpl implements UserService {
	
	private Map<String, UserDTO> users;
	
	@Autowired
	private Utils utils;
	
	@Override
	public UserDTO createUser(UserDetailsDTO userDetalisDTO) {
		String userId = utils.generateUserId();
		UserDTO userDTO = new UserDTO(userDetalisDTO.getFirstName(), userDetalisDTO.getLastName(),
				userDetalisDTO.getEmail(), userId);
		if (users == null)
			users = new HashMap<>();
		users.put(userId, userDTO);
		return userDTO;
	}

}
