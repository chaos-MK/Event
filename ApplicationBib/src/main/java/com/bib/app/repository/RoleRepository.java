package com.bib.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bib.app.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {
    
    Iterable<Role> findByName(String name);
    
    Iterable<Role> findByRequiredSkillsContaining(String skill);
}