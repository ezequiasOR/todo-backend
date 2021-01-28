package com.ezequiasr.todo.exception;

public class RegisterNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public RegisterNotFoundException(String message) {
		super(message);
	}
}
