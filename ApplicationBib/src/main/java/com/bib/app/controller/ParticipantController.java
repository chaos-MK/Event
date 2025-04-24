package com.bib.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bib.app.entities.Participant;
import com.bib.app.service.ParticipantService;

@RestController
@RequestMapping("/participants")
@CrossOrigin(origins = "*")
public class ParticipantController {
    
    private ParticipantService participantService;
    
    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants() {
        return ResponseEntity.ok(participantService.getAllParticipants());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable String id) {
        return participantService.getParticipantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Participant> getParticipantByEmail(@PathVariable String email) {
        return participantService.getParticipantByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/company/{company}")
    public ResponseEntity<Iterable<Participant>> getParticipantsByCompany(@PathVariable String company) {
        return ResponseEntity.ok(participantService.getParticipantsByCompany(company));
    }
    
    @GetMapping("/search")
    public ResponseEntity<Iterable<Participant>> searchParticipantsByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(participantService.searchParticipantsByLastName(lastName));
    }
    
    @PostMapping
    public ResponseEntity<Participant> createParticipant(@RequestBody Participant participant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(participantService.createParticipant(participant));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable String id, @RequestBody Participant participantDetails) {
        return participantService.updateParticipant(id, participantDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable String id) {
        if (participantService.deleteParticipant(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
