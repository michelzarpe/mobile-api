package com.appsdeveloperblog.app.ws.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.model.dto.UpdateUserDetailsDTO;
import com.appsdeveloperblog.app.ws.model.dto.UserDTO;
import com.appsdeveloperblog.app.ws.model.dto.UserDetailsDTO;
import com.appsdeveloperblog.app.ws.response.Response;
import com.appsdeveloperblog.app.ws.service.UserService;


@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
	@Autowired
	UserService userService;
	
	Map<String, UserDTO> users;
	
	@GetMapping()
	public String getUsers(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", defaultValue = "desc" ,required = false) String sort
			) {
		return "Get users was Calletd with page: "+page+" Limite: "+limit +" sort: "+sort;
	}

	@GetMapping(path = "/{userId}",
			produces = {MediaType.APPLICATION_XML_VALUE, 
			            MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserDTO> getUser(@PathVariable String userId){
		
		//if(true) throw new UserServiceException("A user Service exception is throw");
		
		if(users.containsKey(userId)) {
			return ResponseEntity.ok(users.get(userId));
		}else {
			return ResponseEntity.noContent().build();
		}

	}

	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, 
            	             MediaType.APPLICATION_JSON_VALUE},
			     produces = {MediaType.APPLICATION_XML_VALUE, 
   	                         MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<UserDTO>> createUser(@Valid @RequestBody UserDetailsDTO userDetailsDTO, BindingResult result) {
		Response<UserDTO> response = new Response<UserDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		UserDTO userDTO = userService.createUser(userDetailsDTO);
		
		response.setData(userDTO);
		return ResponseEntity.ok(response);
	}

	@PutMapping(path = "/{userId}", 
			    consumes = {MediaType.APPLICATION_XML_VALUE, 
            				MediaType.APPLICATION_JSON_VALUE},
				produces = {MediaType.APPLICATION_XML_VALUE, 
							MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<UserDTO>> updateUser(@PathVariable String userId,
							 @Valid @RequestBody UpdateUserDetailsDTO userDetailsDTO, 
							 BindingResult result) {
		
		Response<UserDTO> response = new Response<UserDTO>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		UserDTO storedUser = users.get(userId);
		
		storedUser.setFirstName(userDetailsDTO.getFirstName());
		storedUser.setLastName(userDetailsDTO.getLastName());
		if(users == null) users = new HashMap<>();
		users.put(userId, storedUser);
		response.setData(storedUser);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		if(users.containsKey(userId)) {
			users.remove(userId);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}

}
