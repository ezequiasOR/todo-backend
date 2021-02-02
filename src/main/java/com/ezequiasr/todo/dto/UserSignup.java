package com.ezequiasr.todo.dto;

import java.util.Set;

import javax.persistence.Column;

import com.ezequiasr.todo.enums.Role;

public class UserSignup {
	
	@Column(nullable = false)
	private String name;
 
	@Column(nullable = false)
	private String username;
 
	@Column(nullable = false)
	private String email;
    
	private Set<Role> role;
    
    @Column(nullable = false)
    private String password;
 
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
    
	public Set<Role> getRole() {
		return this.role;
	}
    
	public void setRole(Set<Role> role) {
		this.role = role;
	}
}
