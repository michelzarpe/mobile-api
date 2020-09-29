package com.appsdeveloperblog.app.ws.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable{
	private static final long serialVersionUID = -6096049234903251988L;

	private String firstName;
	private String lastName;
	private String email;
	private String userId;
	
	
}
