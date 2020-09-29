package com.appsdeveloperblog.app.ws.exeptions;

public class UserServiceException extends RuntimeException{
	private static final long serialVersionUID = -5757576762118051399L;

	public UserServiceException(String message) {
		super(message);
	}
	
}
