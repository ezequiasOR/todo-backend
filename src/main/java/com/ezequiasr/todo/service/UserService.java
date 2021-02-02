package com.ezequiasr.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezequiasr.todo.dto.UserSignup;
import com.ezequiasr.todo.exception.RegisterNotFoundException;
import com.ezequiasr.todo.exception.UserAlreadyExistException;
import com.ezequiasr.todo.model.ToDoList;
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

	public void addToDoList(Long userId, ToDoList toDoList) {
		Optional<User> optUser = userRepository.findById(userId);
		
		if (!optUser.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		User user = optUser.get();
		
		user.addToDoList(toDoList);
	}

	public ToDoList findListById(Long userId, Long listId) {
		Optional<User> optUser = userRepository.findById(userId);
		
		if (!optUser.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		User user = optUser.get();
		
		for (ToDoList toDoList : user.getLists()) {
			if (toDoList.getId() == listId) {
				return toDoList;
			}
		}
		
		return null;
	}
	
	public void deleteList(Long userId, ToDoList toDoList) {
		Optional<User> optUser = userRepository.findById(userId);
		
		if (!optUser.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		User user = optUser.get();
		
		if (user.getLists().contains(toDoList)) {
			user.getLists().remove(toDoList);
		}
	}

}
