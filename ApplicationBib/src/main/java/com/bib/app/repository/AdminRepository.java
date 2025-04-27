package com.bib.app.repository;

import com.bib.app.entities.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, String> {
    Admin findByUsername(String username);
}