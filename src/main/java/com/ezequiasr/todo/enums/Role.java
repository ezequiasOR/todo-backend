package com.ezequiasr.todo.enums;

public enum Role {
	ADMIN(1, "ADMIN"),
	USER(2, "USER");
	
	private int id;
	private String name;
	
	private Role(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public static Role toEnum(Integer id) {
		if (id == null) {
			return null;
		}
		
		for (Role role : Role.values()) {
			if (id == role.getId()) {
				return role;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + id);
	}
}
