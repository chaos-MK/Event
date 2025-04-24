package com.bib.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bib.app.entities.Registration;

@Repository
public interface RegistrationRepository extends CrudRepository<Registration, String> {
    
    List<Registration> findByEventId(String eventId);
    
    List<Registration> findByParticipantId(String participantId);
    
    List<Registration> findByStatus(String status);
    
    List<Registration> findByRegistrationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<Registration> findByEventIdAndStatus(String eventId, String status);
    
    List<Registration> findByAttended(boolean attended);
    
    long countByEventId(String eventId);
    
    long countByEventIdAndAttended(String eventId, boolean attended);
}