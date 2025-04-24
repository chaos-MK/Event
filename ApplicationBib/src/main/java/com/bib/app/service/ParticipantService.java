package com.bib.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bib.app.entities.Participant;
import com.bib.app.repository.ParticipantRepository;

@Service
public class ParticipantService {
    
    private ParticipantRepository participantRepository;
    
    public List<Participant> getAllParticipants() {
        List<Participant> participants = new ArrayList<>();
        participantRepository.findAll().forEach(participants::add);
        return participants;
    }
    
    public Optional<Participant> getParticipantById(String id) {
        return participantRepository.findById(id);
    }
    
    public Optional<Participant> getParticipantByEmail(String email) {
        return participantRepository.findByEmail(email);
    }
    
    public Iterable<Participant> getParticipantsByCompany(String company) {
        return participantRepository.findByCompany(company);
    }
    
    public Iterable<Participant> searchParticipantsByLastName(String lastName) {
        return participantRepository.findByLastNameContaining(lastName);
    }
    
    public Participant createParticipant(Participant participant) {
        participant.setCreatedAt(LocalDateTime.now());
        participant.setUpdatedAt(LocalDateTime.now());
        return participantRepository.save(participant);
    }
    
    public Optional<Participant> updateParticipant(String id, Participant participantDetails) {
        return participantRepository.findById(id).map(existingParticipant -> {
            existingParticipant.setFirstName(participantDetails.getFirstName());
            existingParticipant.setLastName(participantDetails.getLastName());
            existingParticipant.setEmail(participantDetails.getEmail());
            existingParticipant.setPhone(participantDetails.getPhone());
            existingParticipant.setCompany(participantDetails.getCompany());
            existingParticipant.setJobTitle(participantDetails.getJobTitle());
            existingParticipant.setDietaryRestrictions(participantDetails.getDietaryRestrictions());
            existingParticipant.setSpecialNeeds(participantDetails.getSpecialNeeds());
            existingParticipant.setUpdatedAt(LocalDateTime.now());
            return participantRepository.save(existingParticipant);
        });
    }
    
    public boolean deleteParticipant(String id) {
        if (participantRepository.existsById(id)) {
            participantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

