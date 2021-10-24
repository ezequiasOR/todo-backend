package com.ezequiasr.todo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezequiasr.todo.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
	Optional<ToDo> findById(Long id);

    Optional<List<ToDo>> findByListId(Long listId);
}
