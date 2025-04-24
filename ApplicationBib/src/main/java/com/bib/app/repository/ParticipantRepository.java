package com.bib.app.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bib.app.entities.Participant;

import java.util.Optional;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, String> {
    
    Optional<Participant> findByEmail(String email);
    
    Iterable<Participant> findByCompany(String company);
    
    Iterable<Participant> findByLastNameContaining(String lastName);
}
