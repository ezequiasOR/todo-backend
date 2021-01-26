package com.ezequiasr.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "toDos")
public class ToDo {
	
	@Id
	@GeneratedValue
	private long id;
	private String description;
	
	public ToDo() {
		this.id = 0;
	}

	public ToDo(String description) {
		super();
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
