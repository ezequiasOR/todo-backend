package com.ezequiasr.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezequiasr.todo.model.ToDoList;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {
	Optional<ToDoList> findById(Long id);
}
