package com.ezequiasr.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequiasr.todo.exception.RegisterNotFoundException;
import com.ezequiasr.todo.model.ToDoList;
import com.ezequiasr.todo.model.User;
import com.ezequiasr.todo.repository.ToDoListRepository;

@Service
public class ToDoListService {
	private final String errorMessage = "To-Do List not found";

	@Autowired
	private ToDoListRepository toDoListRepository;

	@Autowired
	private UserService userService;

	public ToDoList save(Long userId, ToDoList toDoList) {
		toDoList.setUserId(userId);
		toDoListRepository.save(toDoList);
		return toDoList;
	}

	public List<ToDoList> getAllUserLists(Long userId) {
		User user = userService.getById(userId);
		Optional<List<ToDoList>> optList = toDoListRepository.findByUserId(user.getId());

		if (!optList.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}

		return optList.get();
	}

	public ToDoList getById(Long listId) {
		Optional<ToDoList> optToDoList = toDoListRepository.findById(listId);
		
		if (!optToDoList.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		return optToDoList.get();
	}

	public ToDoList update(Long listId, ToDoList toDoList) {
		Optional<ToDoList> optToDoList = toDoListRepository.findById(listId);
		
		if (!optToDoList.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		ToDoList newToDoList = optToDoList.get();
		newToDoList.setName(toDoList.getName());
		
		toDoListRepository.save(newToDoList);
		return newToDoList;
	}

	public ToDoList delete(Long listId) {
		Optional<ToDoList> optList = toDoListRepository.findById(listId);

		if (!optList.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}

		toDoListRepository.deleteById(listId);
		return optList.get();
	}
}
