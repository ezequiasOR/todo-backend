package com.ezequiasr.todo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezequiasr.todo.dto.UserSignup;
import com.ezequiasr.todo.model.User;
import com.ezequiasr.todo.service.UserService;

@Controller
@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "+")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@Transactional
	public User save(@RequestBody UserSignup user) throws IOException {
		return userService.save(user);
	}

	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAll() {
		return userService.getAll();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User getById(@PathVariable("id") long id) {
		return userService.getById(id);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@PathVariable("id") long id, @RequestBody User user) throws IOException {
		User updatedUser = userService.update(id, user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> delete(@PathVariable("id") long id) {
		User user = userService.delete(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
