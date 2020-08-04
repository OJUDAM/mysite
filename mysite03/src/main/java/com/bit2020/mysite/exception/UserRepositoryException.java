package com.bit2020.mysite.exception;

public class UserRepositoryException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public UserRepositoryException() {
		super("UserRepositoryException Occurs");
	}
}
