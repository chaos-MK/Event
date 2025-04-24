package com.bib.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bib.app.entities.MyUser;

public interface MyUserRepository  extends JpaRepository<MyUser, Long>{
	Optional<MyUser> findByUsername(String username); 
}
