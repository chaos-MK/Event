package com.bib.app.controller;

import com.bib.app.entities.Participant;
import com.bib.app.service.ParticipantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/participants")
@CrossOrigin(origins = "*")
public class ParticipantController {

    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/{eventId}/{email}")
    public ResponseEntity<Participant> getParticipant(@PathVariable String eventId,
                                                    @PathVariable String email) {
        Optional<Participant> participant = participantService.getParticipant(eventId, email);
        return participant.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{eventId}/{email}")
    public ResponseEntity<Void> removeParticipant(@PathVariable String eventId,
                                                @PathVariable String email) {
        participantService.removeParticipant(eventId, email);
        return ResponseEntity.noContent().build();
    }
}