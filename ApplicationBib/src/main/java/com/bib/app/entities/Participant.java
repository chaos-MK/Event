package com.bib.app.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Participant {
    private String email;  // Use email as unique identifier
    private String firstname;
    private String lastname;
    private String phone;
	public Participant(String email2, String firstname2, String lastname2,String phone2) {
		this.email=email2;
		this.firstname=firstname2;
		this.lastname=lastname2;
		this.phone=phone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
}