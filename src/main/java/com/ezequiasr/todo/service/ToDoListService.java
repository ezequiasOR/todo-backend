package com.ezequiasr.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequiasr.todo.exception.RegisterNotFoundException;
import com.ezequiasr.todo.model.ToDo;
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
		userService.addToDoList(userId, toDoList);
		toDoListRepository.save(toDoList);
		return toDoList;
	}

	public List<ToDoList> getAll(Long userId) {
		User user = userService.getById(userId);
		return user.getLists();
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

	public ToDoList delete(Long userId, Long listId) {
		ToDoList toDoList = userService.findListById(userId, listId);
		
		if (toDoList == null) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		userService.deleteList(userId, toDoList);
		
		toDoListRepository.delete(toDoList);
		return toDoList;
	}

	public void addToDo(Long idList, ToDo toDo) {
		Optional<ToDoList> optToDoList = toDoListRepository.findById(idList);
		
		if (!optToDoList.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		ToDoList toDoList = optToDoList.get();
		
		toDoList.addToDo(toDo);
	}

	public ToDo findToDoById(Long idList, Long idToDo) {
		Optional<ToDoList> optToDoList = toDoListRepository.findById(idList);
		
		if (!optToDoList.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		ToDoList toDoList = optToDoList.get();
		
		for (ToDo toDo : toDoList.getToDos()) {
			if (toDo.getId() == idToDo) {
				return toDo;
			}
		}
		
		return null;
	}

	public void deleteToDo(Long idList, ToDo toDo) {
		Optional<ToDoList> optToDoList = toDoListRepository.findById(idList);
		
		if (!optToDoList.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		ToDoList toDoList = optToDoList.get();
		
		if (toDoList.getToDos().contains(toDo)) {
			toDoList.getToDos().remove(toDo);
		}
	}
}
