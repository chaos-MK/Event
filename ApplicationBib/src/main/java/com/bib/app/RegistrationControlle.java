package com.bib.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bib.app.entities.MyUser;
import com.bib.app.repository.MyUserRepository;
@RestController
public class RegistrationControlle {
	 @Autowired 
	    private MyUserRepository myUserRepository; 
	    @Autowired 
	    private PasswordEncoder passwordEncoder; 
	 
	    @PostMapping("/register/user")
	    public MyUser createUser(@RequestBody MyUser user) { 
	        user.setPassword(passwordEncoder.encode(user.getPassword())); 
	        return myUserRepository.save(user); 
	    } 
}
