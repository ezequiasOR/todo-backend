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
	
	@RequestMapping(value = "/list/{id}/todo", method = RequestMethod.POST)
	public ToDo save(@PathVariable("id") long idList, @RequestBody ToDo entity) throws IOException {
		return toDoService.save(idList, entity);
	}
	
	@RequestMapping(value = "/list/{id}/todo", method = RequestMethod.GET)
	public List<ToDo> getAll(@PathVariable("id") long idList) {
		return toDoService.getAll(idList);
	}
	
	@RequestMapping(value = "/list/{idList}/todo/{idToDo}", method = RequestMethod.GET)
	public ToDo getById(@PathVariable("idList") long idList, @PathVariable("idToDo") long idToDo) {
		return toDoService.getById(idList, idToDo);
	}
	
	@RequestMapping(value = "/list/{idList}/todo/{idToDo}", method = RequestMethod.PUT)
	public ResponseEntity<ToDo> update(@PathVariable("idList") long idList, @PathVariable("idToDo") long idToDo,
			@RequestBody ToDo toDo) throws IOException {
		ToDo updatedToDo = toDoService.update(idList, idToDo, toDo);
		return new ResponseEntity<ToDo>(updatedToDo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/list/{idList}/todo/{idToDo}", method = RequestMethod.DELETE)
	public ResponseEntity<ToDo> delete(@PathVariable("idList") long idList, @PathVariable("idToDo") long idToDo) {
		ToDo toDo = toDoService.delete(idList, idToDo);
		return new ResponseEntity<ToDo>(toDo, HttpStatus.OK);
	}
	
}
