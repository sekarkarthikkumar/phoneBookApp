package com.cisco.phonebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserDetailNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4139710908882285015L;

	public UserDetailNotFoundException(String message) {
		 super(message);
	}
	
}
