package com.bib.app.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("event")
public class Event {
    @Id
    private String id = UUID.randomUUID().toString();

    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String location;
    private int capacity;
    private String status;
    private String registrationType;
    private String imageUrl;
    
    // Relationships (Reference by IDs)
    private String categoryId;
    private List<String> staffIds;  // Staff IDs
    private List<String> participantEmails;  // Participant emails
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public List<String> getStaffIds() {
		return staffIds;
	}
	public void setStaffIds(List<String> staffIds) {
		this.staffIds = staffIds;
	}
	public List<String> getParticipantEmails() {
		return participantEmails;
	}
	public void setParticipantEmails(List<String> participantEmails) {
		this.participantEmails = participantEmails;
	}
    
}