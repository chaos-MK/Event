package com.bib.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bib.app.entities.Staff;

@Repository
public interface StaffRepository extends CrudRepository<Staff, String> {
    
    Optional<Staff> findByEmail(String email);
    
    Iterable<Staff> findBySkillsContaining(String skill);
    
    Iterable<Staff> findByPosition(String position);
}
