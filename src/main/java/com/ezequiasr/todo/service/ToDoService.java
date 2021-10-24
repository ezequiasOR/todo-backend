package com.ezequiasr.todo.service;

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

	public ToDo save(ToDo toDo) {
		toDoRepository.save(toDo);
		return toDo;
	}

	public List<ToDo> getAll(Long listId) {
		Optional<List<ToDo>> optToDos = toDoRepository.findByListId(listId);

		if (!optToDos.isPresent()) {
			throw new RegisterNotFoundException("List not found");
		}

		return optToDos.get();
	}

	public ToDo getById(Long toDoId) {
		Optional<ToDo> optToDo = toDoRepository.findById(toDoId);
		
		if (!optToDo.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		return optToDo.get();
	}

	public ToDo update(Long toDoId, ToDo toDo) {
		Optional<ToDo> optToDo = toDoRepository.findById(toDoId);
		
		if (!optToDo.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		ToDo newToDo = optToDo.get();
		newToDo.setDescription(toDo.getDescription());
		newToDo.setDtToDo(toDo.getDtToDo());
		newToDo.setCompleted(toDo.getCompleted());
		newToDo.setListId(toDo.getListId());
		
		toDoRepository.save(newToDo);
		return newToDo;
	}

	public ToDo delete(Long idToDo) {
		Optional<ToDo> optToDo = toDoRepository.findById(idToDo);

		if (!optToDo.isPresent()) {
			throw new RegisterNotFoundException(errorMessage);
		}

		ToDo toDo = optToDo.get();
		toDoRepository.delete(toDo);
		return toDo;
	}
}
