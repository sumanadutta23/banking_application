package com.jwt.helper;

public class UserFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8882949510099818946L;
	public UserFoundException() {
		super("User with the username already there in the Database !!");
	}
	public UserFoundException(String msg) { 
		super(msg); 
	}
}
