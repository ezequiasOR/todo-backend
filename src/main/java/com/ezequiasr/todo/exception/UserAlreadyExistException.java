package com.ezequiasr.todo.exception;

public class UserAlreadyExistException extends RuntimeException {
	public UserAlreadyExistException() {
		super("Already exists a user registered with this email!");
	}
}
