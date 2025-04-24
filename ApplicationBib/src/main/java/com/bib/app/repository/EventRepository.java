package com.bib.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bib.app.entities.Event;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, String> {
    
    List<Event> findByCategory(String category);
    
    List<Event> findByStatus(String status);
    
    List<Event> findByStartDateAfter(LocalDateTime date);
    
    List<Event> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<Event> findByTitleContaining(String title);
}

