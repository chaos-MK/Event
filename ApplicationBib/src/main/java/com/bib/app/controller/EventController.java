package com.bib.app.controller;

import com.bib.app.entities.Event;
import com.bib.app.entities.Participant;
import com.bib.app.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(eventService.createEvent(event));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Event>> getEventsByCategory(@PathVariable String categoryId) {
        return ResponseEntity.ok(eventService.getEventsByCategory(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @RequestBody Event event) {
        return eventService.updateEvent(id, event)
            .map(updatedEvent -> ResponseEntity.ok(updatedEvent))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eventId}/staff/{staffId}")
    public ResponseEntity<Void> assignStaff(@PathVariable String eventId, 
                                          @PathVariable String staffId) {
        eventService.assignStaffToEvent(eventId, staffId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{eventId}/participants")
    public ResponseEntity<Void> addParticipant(@PathVariable String eventId,
                                             @RequestBody Participant participant) {
        eventService.addParticipant(eventId, participant);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}