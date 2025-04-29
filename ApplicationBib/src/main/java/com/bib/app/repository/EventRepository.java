package com.bib.app.repository;

import com.bib.app.entities.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, String> {
    List<Event> findByCategoryId(String categoryId);
    List<Event> findByStatus(String status);
}