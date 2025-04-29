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
    private String username;
    
    private String id = UUID.randomUUID().toString();
    private String password;
    private String role;
    private List<String> staffIds;
    
    // Lombok @Data should handle getters/setters, but keeping these for compatibility
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
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
    
    // Fixed method names to match field name
    public List<String> getStaffIds() {
        return staffIds;
    }
    
    public void setStaffIds(List<String> staffIds) {
        this.staffIds = staffIds;
    }
    
    // Keep these for backward compatibility if needed
    public List<String> getStaffsID() {
        return staffIds;
    }
    
    public void setStaffs(List<String> staffIds) {
        this.staffIds = staffIds;
    }
}