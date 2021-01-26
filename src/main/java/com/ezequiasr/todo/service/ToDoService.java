package com.ezequiasr.todo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequiasr.todo.exception.RegisterNotFoundException;
import com.ezequiasr.todo.model.ToDo;
import com.ezequiasr.todo.model.ToDoList;
import com.ezequiasr.todo.repository.ToDoRepository;

@Service
public class ToDoService {
	
	private final String errorMessage = "To-Do not found";
	
	@Autowired
	private ToDoRepository toDoRepository;
	
	@Autowired
	private ToDoListService toDoListService;

	public ToDo save(Long idList, ToDo toDo) throws IOException {
		toDoListService.addToDo(idList, toDo);
		toDoRepository.save(toDo);
		return toDo;
	}
	
	public List<ToDo> getAll(Long idList) {
		ToDoList toDoList = toDoListService.getById(idList);
		return toDoList.getToDos();
	}

	public ToDo getById(Long idList, Long idToDo) {
		ToDo toDo = toDoListService.findToDoById(idList, idToDo);
		
		if (toDo == null) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		return toDo;
	}
	
	public ToDo update(Long idList, Long idToDo, ToDo toDo) throws IOException {
		ToDo newToDo = toDoListService.findToDoById(idList, idToDo);
		
		if (newToDo == null) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		newToDo.setDescription(toDo.getDescription());
		
		toDoRepository.save(newToDo);
		return newToDo;
	}
	
	public ToDo delete(Long idList, Long idToDo) {
		ToDo toDo = toDoListService.findToDoById(idList, idToDo);
		
		if (toDo == null) {
			throw new RegisterNotFoundException(errorMessage);
		}
		
		toDoListService.deleteToDo(idList, toDo);
		
		toDoRepository.delete(toDo);
		return toDo;
	}
	
}
