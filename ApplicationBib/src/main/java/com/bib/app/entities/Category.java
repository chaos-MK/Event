package com.bib.app.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Category {

    private String categoryId = UUID.randomUUID().toString(); // Auto-generate
    private String type;
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}