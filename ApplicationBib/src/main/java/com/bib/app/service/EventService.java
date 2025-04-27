package com.bib.app.service;

import com.bib.app.entities.Event;
import com.bib.app.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        eventRepository.findAll().forEach(events::add);
        return events;
    }
    
    public Optional<Event> getEventById(String id) {
        return eventRepository.findById(id);
    }
    
    public List<Event> getEventsByCategory(String category) {
        return eventRepository.findByCategory(category);
    }
    
    public List<Event> getEventsByStatus(String status) {
        return eventRepository.findByStatus(status);
    }
    
    public List<Event> getUpcomingEvents() {
        return eventRepository.findByStartDateAfter(LocalDateTime.now());
    }
    
    public List<Event> getEventsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return eventRepository.findByStartDateBetween(startDate, endDate);
    }
    
    public List<Event> searchEventsByTitle(String title) {
        return eventRepository.findByTitleContaining(title);
    }
    
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
    
    public Optional<Event> updateEvent(String id, Event eventDetails) {
        return eventRepository.findById(id).map(existingEvent -> {
            existingEvent.setTitle(eventDetails.getTitle());
            existingEvent.setDescription(eventDetails.getDescription());
            existingEvent.setStartDate(eventDetails.getStartDate());
            existingEvent.setEndDate(eventDetails.getEndDate());
            existingEvent.setLocation(eventDetails.getLocation());
            existingEvent.setCapacity(eventDetails.getCapacity());
            existingEvent.setCategory(eventDetails.getCategory());
            existingEvent.setStatus(eventDetails.getStatus());
            existingEvent.setRegistrationType(eventDetails.getRegistrationType());
            existingEvent.setImageUrl(eventDetails.getImageUrl());
            return eventRepository.save(existingEvent);
        });
    }
    
    public boolean deleteEvent(String id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }
}