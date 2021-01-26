package com.ezequiasr.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequiasr.todo.exception.RegisterNotFoundException;
import com.ezequiasr.todo.exception.UserAlreadyExistException;
import com.ezequiasr.todo.model.User;
import com.ezequiasr.todo.repository.UserRepository;

@Service
public class UserService {

	private final String errorMessage = "The User is not registered";
	
	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		Optional<User> optUser = userRepository.findByEmail(user.getEmail());

		if (optUser.isPresent()) {
			throw new UserAlreadyExistException();
		}
		
		userRepository.save(user);
		return user;
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getById(long id) {
		Optional<User> optUser = userRepository.findById(id);
		
		if(!optUser.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		return optUser.get();
	}

	public User update(long id, User user) {
		Optional<User> optUser = userRepository.findById(id);
		
		if(!optUser.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}

		User newUser = optUser.get();
		newUser.setName(user.getName());
		newUser.setPassword(user.getPassword());
		
		userRepository.save(newUser);
		return newUser;
	}

	public User delete(long id) {
		Optional<User> optUser = userRepository.findById(id);
		
		if(!optUser.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		User user = optUser.get();
		
		userRepository.delete(user);
		return user;
	}
}
