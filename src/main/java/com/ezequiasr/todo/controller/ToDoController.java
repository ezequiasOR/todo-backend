package com.ezequiasr.todo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezequiasr.todo.model.ToDo;
import com.ezequiasr.todo.service.ToDoService;

@Controller
@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "+")
public class ToDoController {

	@Autowired
	private ToDoService toDoService;
	
	@RequestMapping(value = "/list/{listId}/todo", method = RequestMethod.POST)
	public ToDo save(@PathVariable("listId") long listId, @RequestBody ToDo toDo) throws IOException {
		return toDoService.save(listId, toDo);
	}
	
	@RequestMapping(value = "/list/{listId}/todo", method = RequestMethod.GET)
	public List<ToDo> getAll(@PathVariable("listId") long listId) {
		return toDoService.getAll(listId);
	}
	
	@RequestMapping(value = "/todo/{toDoId}", method = RequestMethod.GET)
	public ToDo getById(@PathVariable("toDoId") long toDoId) {
		return toDoService.getById(toDoId);
	}
	
	@RequestMapping(value = "/todo/{toDoId}", method = RequestMethod.PUT)
	public ResponseEntity<ToDo> update(@PathVariable("toDoId") long toDoId,
			@RequestBody ToDo toDo) throws IOException {
		ToDo updatedToDo = toDoService.update(toDoId, toDo);
		return new ResponseEntity<ToDo>(updatedToDo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{listId}/todo/{toDoId}", method = RequestMethod.DELETE)
	public ResponseEntity<ToDo> delete(@PathVariable("listId") long listId, @PathVariable("toDoId") long toDoId) {
		ToDo toDo = toDoService.delete(listId, toDoId);
		return new ResponseEntity<ToDo>(toDo, HttpStatus.OK);
	}
	
}
