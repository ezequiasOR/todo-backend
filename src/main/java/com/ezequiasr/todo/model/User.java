package com.ezequiasr.todo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ezequiasr.todo.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name = "users")
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles")
	private Set<Integer> roles = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ToDoList> lists = new ArrayList<>();
	
	public User() {
		this.id = 0;
//		this.roles.add(Role.USER.getId());
	}
	
	public User(String name, String username, String email, String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		if (email.equals("ezequias.rocha9@gmail.com")) {
			this.roles.add(Role.ADMIN.getId());
		} else {
			this.roles.add(Role.USER.getId());
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List<Role> getRoles() {
		return this.roles.stream().map(role -> Role.toEnum(role)).collect(Collectors.toList());
	}

	public void setRoles(Set<Integer> roles) {
		this.roles = roles;
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
