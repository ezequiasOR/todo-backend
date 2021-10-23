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
@CrossOrigin(origins = "*")
public class ToDoListController {

	@Autowired
	private ToDoListService toDoListService;
	
	@RequestMapping(value = "/user/{userId}/list", method = RequestMethod.POST)
	private ToDoList save(@PathVariable("userId") long userId, @RequestBody ToDoList toDoList) throws IOException {
		return toDoListService.save(userId, toDoList);
	}
	
	@RequestMapping(value = "/user/{userId}/list", method = RequestMethod.GET)
	public List<ToDoList> getAll(@PathVariable("userId") long userId) {
		return toDoListService.getAll(userId);
	}
	
	@RequestMapping(value = "/list/{listId}", method = RequestMethod.GET)
	public ToDoList getById(@PathVariable("listId") long listId) {
		return toDoListService.getById(listId);
	}
	
	@RequestMapping(value = "/list/{listId}", method = RequestMethod.PUT)
	public ResponseEntity<ToDoList> update(@PathVariable("listId") long listId, @RequestBody ToDoList toDoList) throws IOException {
		ToDoList updatedToDoList = toDoListService.update(listId, toDoList);
		return new ResponseEntity<ToDoList>(updatedToDoList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{userId}/list/{listId}", method = RequestMethod.DELETE)
	public ResponseEntity<ToDoList> delete(@PathVariable("userId") long userId, @PathVariable("listId") long listId) {
		ToDoList toDoList = toDoListService.delete(userId, listId);
		return new ResponseEntity<ToDoList>(toDoList, HttpStatus.OK);
	}
	
}
