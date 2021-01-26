package com.ezequiasr.todo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ToDoList {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ToDo> toDos = new ArrayList<>();
	
	public ToDoList() {
		this.id = 0;
	}

	public ToDoList(String name) {
		super();
		this.name = name;
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

	public List<ToDo> getToDos() {
		return toDos;
	}

	public void setToDos(List<ToDo> toDos) {
		this.toDos = toDos;
	}
	
	public void addToDo(ToDo toDo) {
		this.toDos.add(toDo);
	}
}
