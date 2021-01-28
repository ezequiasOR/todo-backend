package com.ezequiasr.todo.exception;

public class UserAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException() {
		super("Already exists a user registered with this email!");
	}
}
