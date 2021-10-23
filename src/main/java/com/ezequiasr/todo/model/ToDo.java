package com.ezequiasr.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "toDos")
public class ToDo {
	
	@Id
	@GeneratedValue
	private long id;
	private String description;
	private LocalDateTime dtToDo;
	private Boolean completed;
	
	public ToDo() {
		this.id = 0;
		this.completed = false;
	}

	public ToDo(String description, LocalDateTime dtToDo, Boolean completed) {
		super();
		this.description = description;
		this.dtToDo = dtToDo;
		this.completed = completed;
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

	public LocalDateTime getDtToDo() {
		return dtToDo;
	}

	public void setDtToDo(LocalDateTime dtToDo) {
		this.dtToDo = dtToDo;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
}
