package com.ezequiasr.todo.model;

import javax.persistence.*;

@Entity
public class ToDoList {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Long userId;

	public ToDoList() {
		this.id = Long.valueOf(0);
	}

	public ToDoList(String name, Long userId) {
		super();
		this.name = name;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
