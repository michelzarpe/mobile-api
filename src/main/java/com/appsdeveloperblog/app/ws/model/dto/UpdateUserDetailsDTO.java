package com.appsdeveloperblog.app.ws.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserDetailsDTO implements Serializable{
	private static final long serialVersionUID = -6096049234903251988L;

    @NotNull(message = "First name cannot be missing or empty")
    @Size(min = 2, message = "First name must not be less than 2 characteres")
	private String firstName;
    @NotNull(message = "Last name cannot be missing or empty")
    @Size(min = 2, message = "First name must not be less than 2 characteres")
    private String lastName;	
	
}
