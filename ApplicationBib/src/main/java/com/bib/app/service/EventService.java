package com.bib.app.service;

import com.bib.app.entities.Event;
import com.bib.app.entities.Participant;
import com.bib.app.entities.Staff;
import com.bib.app.repository.EventRepository;
import com.bib.app.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final StaffRepository staffRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public EventService(EventRepository eventRepository, 
                      StaffRepository staffRepository,
                      RedisTemplate<String, Object> redisTemplate) {
        this.eventRepository = eventRepository;
        this.staffRepository = staffRepository;
        this.redisTemplate = redisTemplate;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    public Optional<Event> getEventById(String id) {
        return eventRepository.findById(id);
    }

    public List<Event> getEventsByCategory(String categoryId) {
        return eventRepository.findByCategoryId(categoryId);
    }


    public Optional<Event> updateEvent(String id, Event eventDetails) {
        return eventRepository.findById(id)
            .map(existingEvent -> {
                existingEvent.setTitle(eventDetails.getTitle());
                existingEvent.setDescription(eventDetails.getDescription());
                existingEvent.setStartDate(eventDetails.getStartDate());
                existingEvent.setEndDate(eventDetails.getEndDate());
                existingEvent.setLocation(eventDetails.getLocation());
                existingEvent.setCapacity(eventDetails.getCapacity());
                existingEvent.setStatus(eventDetails.getStatus());
                existingEvent.setRegistrationType(eventDetails.getRegistrationType());
                existingEvent.setImageUrl(eventDetails.getImageUrl());
                existingEvent.setCategoryId(eventDetails.getCategoryId());
                return eventRepository.save(existingEvent);
            });
    }
    
    public void addParticipant(String eventId, Participant participant) {
        String participantKey = "event:" + eventId + ":participant:" + participant.getEmail();
        redisTemplate.opsForValue().set(participantKey, participant);
        redisTemplate.opsForSet().add("event:" + eventId + ":participants", participant.getEmail());
    }

    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
        redisTemplate.delete("event:" + id + ":staff");
        redisTemplate.delete("event:" + id + ":participants");
    }

    public void assignStaffToEvent(String eventId, String staffId) {
        staffRepository.findById(staffId).ifPresent(staff -> {
            if (Boolean.TRUE.equals(staff.getAvailability())) {
                redisTemplate.opsForSet().add("event:" + eventId + ":staff", staffId);
            }
        });
    }

    public void addParticipant(String eventId, String email, String firstname, String lastname, String phone) {
        String participantKey = "event:" + eventId + ":participant:" + email;
        redisTemplate.opsForValue().set(participantKey, new Participant(email, firstname, lastname, phone));
        redisTemplate.opsForSet().add("event:" + eventId + ":participants", email);
    }
}