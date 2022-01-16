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

import com.ezequiasr.todo.model.ToDo;
import com.ezequiasr.todo.service.ToDoService;

@Controller
@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*")
public class ToDoController {

	@Autowired
	private ToDoService toDoService;
	
	@RequestMapping(value = "/todo", method = RequestMethod.POST)
	public ResponseEntity<ToDo> save(@RequestBody ToDo toDo) {
		return new ResponseEntity<ToDo>(toDoService.save(toDo), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/list/{listId}/todo", method = RequestMethod.GET)
	public ResponseEntity<List<ToDo>> getAll(@PathVariable("listId") Long listId) {
		return new ResponseEntity<List<ToDo>>(toDoService.getAll(listId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todo/{toDoId}", method = RequestMethod.GET)
	public ResponseEntity<ToDo> getById(@PathVariable("toDoId") Long toDoId) {
		return new ResponseEntity<ToDo>(toDoService.getById(toDoId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todo/{toDoId}", method = RequestMethod.PUT)
	public ResponseEntity<ToDo> update(@PathVariable("toDoId") Long toDoId,
			@RequestBody ToDo toDo) {
		ToDo updatedToDo = toDoService.update(toDoId, toDo);
		return new ResponseEntity<ToDo>(updatedToDo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todo/{toDoId}", method = RequestMethod.DELETE)
	public ResponseEntity<ToDo> delete(@PathVariable("toDoId") Long toDoId) {
		ToDo toDo = toDoService.delete(toDoId);
		return new ResponseEntity<ToDo>(toDo, HttpStatus.OK);
	}
}
