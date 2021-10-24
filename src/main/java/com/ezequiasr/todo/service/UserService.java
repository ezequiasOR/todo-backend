package com.ezequiasr.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezequiasr.todo.dto.UserSignup;
import com.ezequiasr.todo.exception.RegisterNotFoundException;
import com.ezequiasr.todo.exception.UserAlreadyExistException;
import com.ezequiasr.todo.model.User;
import com.ezequiasr.todo.repository.UserRepository;

@Service
public class UserService {

	private final String errorMessage = "The User is not registered";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	public User save(UserSignup user) {
		if(userRepository.existsByUsername(user.getUsername())) {
			throw new UserAlreadyExistException("Already exists a user registered with this username!");
        }
		
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new UserAlreadyExistException("Already exists a user registered with this email!");
		}
		
		User newUser = new User(user.getName(), user.getUsername(), user.getEmail(),
				encoder.encode(user.getPassword()));
		
		userRepository.save(newUser);
		return newUser;
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User getById(Long id) {
		Optional<User> optUser = userRepository.findById(id);
		
		if(!optUser.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		return optUser.get();
	}

	public User update(Long id, User user) {
		Optional<User> optUser = userRepository.findById(id);
		
		if(!optUser.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
	
		User newUser = optUser.get();
		newUser.setName(user.getName());
		newUser.setPassword(encoder.encode(user.getPassword()));
		
		userRepository.save(newUser);
		return newUser;
	}

	public User delete(Long id) {
		Optional<User> optUser = userRepository.findById(id);
		
		if(!optUser.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		User user = optUser.get();
		userRepository.delete(user);
		return user;
	}
}
