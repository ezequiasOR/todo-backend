package com.ezequiasr.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezequiasr.todo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(Long id);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
}
