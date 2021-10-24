package com.ezequiasr.todo.controller;

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
	private ResponseEntity<ToDoList> save(@PathVariable("userId") Long userId, @RequestBody ToDoList toDoList) {
		return new ResponseEntity<ToDoList>(toDoListService.save(userId, toDoList), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/user/{userId}/list", method = RequestMethod.GET)
	public ResponseEntity<List<ToDoList>> getAll(@PathVariable("userId") Long userId) {
		return new ResponseEntity<List<ToDoList>>(toDoListService.getAllUserLists(userId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{listId}", method = RequestMethod.GET)
	public ToDoList getById(@PathVariable("listId") Long listId) {
		return toDoListService.getById(listId);
	}
	
	@RequestMapping(value = "/list/{listId}", method = RequestMethod.PUT)
	public ResponseEntity<ToDoList> update(@PathVariable("listId") Long listId, @RequestBody ToDoList toDoList) {
		ToDoList updatedToDoList = toDoListService.update(listId, toDoList);
		return new ResponseEntity<ToDoList>(updatedToDoList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{listId}", method = RequestMethod.DELETE)
	public ResponseEntity<ToDoList> delete(@PathVariable("listId") Long listId) {
		ToDoList list = toDoListService.delete(listId);
		return new ResponseEntity<ToDoList>(list, HttpStatus.OK);
	}
	
}
