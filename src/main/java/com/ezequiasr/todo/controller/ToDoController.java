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
	
	@RequestMapping(value = "/todo", method = RequestMethod.POST)
	public ToDo save(@RequestBody ToDo entity) throws IOException {
		return toDoService.save(entity);
	}
	
	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public List<ToDo> getAll() {
		return toDoService.getAll();
	}
	
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	public ToDo getById(@PathVariable("id") long id) {
		return toDoService.getById(id);
	}
	
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ToDo> update(@PathVariable("id") long id, @RequestBody ToDo toDo) throws IOException {
		ToDo updatedToDo = toDoService.update(id, toDo);
		return new ResponseEntity<ToDo>(updatedToDo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ToDo> delete(@PathVariable("id") long id) {
		ToDo toDo = toDoService.delete(id);
		return new ResponseEntity<ToDo>(toDo, HttpStatus.OK);
	}
	
}
