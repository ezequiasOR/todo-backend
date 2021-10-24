package com.ezequiasr.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "toDos")
public class ToDo {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private LocalDateTime dtToDo;

	@Column(nullable = false)
	private Boolean completed;

	@Column(nullable = false)
	private Long listId;

	public ToDo() {
		this.id =  Long.valueOf(0);
		this.completed = false;
	}

	public ToDo(String description, LocalDateTime dtToDo, Boolean completed, Long listId) {
		super();
		this.description = description;
		this.dtToDo = dtToDo;
		this.completed = completed;
		this.listId = listId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}
}
