package com.ezequiasr.todo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequiasr.todo.exception.RegisterNotFoundException;
import com.ezequiasr.todo.model.ToDo;
import com.ezequiasr.todo.repository.ToDoRepository;

@Service
public class ToDoService {
	
	private final String errorMessage = "To-Do not found";
	
	@Autowired
	private ToDoRepository toDoRepository;

	public ToDo save(ToDo toDo) throws IOException {
		toDoRepository.save(toDo);
		return toDo;
	}
	
	public List<ToDo> getAll() {
		return toDoRepository.findAll();
	}

	public ToDo getById(long id) {
		Optional<ToDo> optToDo = toDoRepository.findById(id);
		
		if (!optToDo.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		return optToDo.get();
	}
	
	public ToDo update(long id, ToDo toDo) throws IOException {
		Optional<ToDo> optToDo = toDoRepository.findById(id);
		
		if (!optToDo.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		ToDo newToDo = optToDo.get();
		newToDo.setDescription(toDo.getDescription());
		
		toDoRepository.save(newToDo);
		return newToDo;
	}
	
	public ToDo delete(long id) {
		Optional<ToDo> optToDo = toDoRepository.findById(id);
		
		if (!optToDo.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		ToDo toDo = optToDo.get();
		
		toDoRepository.delete(toDo);
		return toDo;
	}
	
}
