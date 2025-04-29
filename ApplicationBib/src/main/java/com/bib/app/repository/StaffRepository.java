package com.bib.app.repository;

import com.bib.app.entities.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends CrudRepository<Staff, String> {
    Optional<Staff> findByEmail(String email);
    Iterable<Staff> findBySkillsContaining(String skill);
	List<Staff> findByAvailability(boolean b);
}