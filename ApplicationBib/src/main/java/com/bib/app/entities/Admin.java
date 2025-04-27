package com.bib.app.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.UUID;

@Data
@RedisHash("admin")
public class Admin {

    @Id
    private String id = UUID.randomUUID().toString();

    private String username;
    private String password;
    private String role;

    private List<Staff> staffs; // Embedded list of Staff

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}
}