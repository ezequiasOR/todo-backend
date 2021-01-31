package com.ezequiasr.todo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ezequiasr.todo.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name = "users")
public class User {
	private Role role;
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;

	
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ToDoList> lists = new ArrayList<>();
	
	public User() {
		this.id = 0;
//		this.role = Role.USER;
	}
	
	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		if (email.equals("ezequias.rocha9@gmail.com")) {
			this.role = Role.ADMIN;
		} else {
			this.role = Role.USER;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<ToDoList> getLists() {
		return lists;
	}

	public void setLists(List<ToDoList> lists) {
		this.lists = lists;
	}

	public void addToDoList(ToDoList toDoList) {
		this.lists.add(toDoList);
	}
}
