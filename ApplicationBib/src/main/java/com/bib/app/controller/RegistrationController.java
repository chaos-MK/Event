package com.bib.app.controller;
import com.bib.app.entities.Registration;
import com.bib.app.service.RegistrationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/registrations")
@CrossOrigin(origins = "*")
public class RegistrationController {
    
    private RegistrationService registrationService = new RegistrationService();
    
    @GetMapping
    public ResponseEntity<Iterable<Registration>> getAllRegistrations() {
        return ResponseEntity.ok(registrationService.getAllRegistrations());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable String id) {
        return registrationService.getRegistrationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Registration>> getRegistrationsByEventId(@PathVariable String eventId) {
        return ResponseEntity.ok(registrationService.getRegistrationsByEventId(eventId));
    }
    
    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<Registration>> getRegistrationsByParticipantId(@PathVariable String participantId) {
        return ResponseEntity.ok(registrationService.getRegistrationsByParticipantId(participantId));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Registration>> getRegistrationsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(registrationService.getRegistrationsByStatus(status));
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<Registration>> getRegistrationsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(registrationService.getRegistrationsByDateRange(startDate, endDate));
    }
    
    @GetMapping("/event/{eventId}/status/{status}")
    public ResponseEntity<List<Registration>> getRegistrationsByEventAndStatus(
            @PathVariable String eventId, @PathVariable String status) {
        return ResponseEntity.ok(registrationService.getRegistrationsByEventAndStatus(eventId, status));
    }
    
    @GetMapping("/attendance")
    public ResponseEntity<List<Registration>> getRegistrationsByAttendance(@RequestParam boolean attended) {
        return ResponseEntity.ok(registrationService.getRegistrationsByAttendance(attended));
    }
    
    @GetMapping("/event/{eventId}/count")
    public ResponseEntity<Long> countRegistrationsByEvent(@PathVariable String eventId) {
        return ResponseEntity.ok(registrationService.countRegistrationsByEvent(eventId));
    }
    
    @GetMapping("/event/{eventId}/attendance/count")
    public ResponseEntity<Long> countAttendanceByEvent(
            @PathVariable String eventId, @RequestParam boolean attended) {
        return ResponseEntity.ok(registrationService.countAttendanceByEvent(eventId, attended));
    }
    
    @PostMapping
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration registration) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.createRegistration(registration));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable String id, @RequestBody Registration registrationDetails) {
        return registrationService.updateRegistration(id, registrationDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable String id) {
        if (registrationService.deleteRegistration(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
