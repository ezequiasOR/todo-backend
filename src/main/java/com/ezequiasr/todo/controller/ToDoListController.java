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

import com.ezequiasr.todo.model.ToDoList;
import com.ezequiasr.todo.service.ToDoListService;

@Controller
@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "+")
public class ToDoListController {

	@Autowired
	private ToDoListService toDoListService;
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	private ToDoList save(@RequestBody ToDoList toDoList) throws IOException {
		return toDoListService.save(toDoList);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<ToDoList> getAll() {
		return toDoListService.getAll();
	}
	
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public ToDoList getById(@PathVariable("id") long id) {
		return toDoListService.getById(id);
	}
	
	@RequestMapping(value = "/list/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ToDoList> update(@PathVariable("id") long id, @RequestBody ToDoList toDoList) throws IOException {
		ToDoList updatedToDoList = toDoListService.update(id, toDoList);
		return new ResponseEntity<ToDoList>(updatedToDoList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ToDoList> delete(@PathVariable("id") long id) {
		ToDoList toDoList = toDoListService.delete(id);
		return new ResponseEntity<ToDoList>(toDoList, HttpStatus.OK);
	}
}
