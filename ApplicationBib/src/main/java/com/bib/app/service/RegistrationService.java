package com.bib.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bib.app.entities.Registration;
import com.bib.app.repository.RegistrationRepository;

@Service
public class RegistrationService {
    
    private RegistrationRepository registrationRepository;
    
    public Iterable<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }
    
    public Optional<Registration> getRegistrationById(String id) {
        return registrationRepository.findById(id);
    }
    
    public List<Registration> getRegistrationsByEventId(String eventId) {
        return registrationRepository.findByEventId(eventId);
    }
    
    public List<Registration> getRegistrationsByParticipantId(String participantId) {
        return registrationRepository.findByParticipantId(participantId);
    }
    
    public List<Registration> getRegistrationsByStatus(String status) {
        return registrationRepository.findByStatus(status);
    }
    
    public List<Registration> getRegistrationsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return registrationRepository.findByRegistrationDateBetween(startDate, endDate);
    }
    
    public List<Registration> getRegistrationsByEventAndStatus(String eventId, String status) {
        return registrationRepository.findByEventIdAndStatus(eventId, status);
    }
    
    public List<Registration> getRegistrationsByAttendance(boolean attended) {
        return registrationRepository.findByAttended(attended);
    }
    
    public long countRegistrationsByEvent(String eventId) {
        return registrationRepository.countByEventId(eventId);
    }
    
    public long countAttendanceByEvent(String eventId, boolean attended) {
        return registrationRepository.countByEventIdAndAttended(eventId, attended);
    }
    
    public Registration createRegistration(Registration registration) {
        registration.setRegistrationDate(LocalDateTime.now());
        return registrationRepository.save(registration);
    }
    
    public Optional<Registration> updateRegistration(String id, Registration registrationDetails) {
        return registrationRepository.findById(id).map(existingRegistration -> {
            existingRegistration.setEventId(registrationDetails.getEventId());
            existingRegistration.setParticipantId(registrationDetails.getParticipantId());
            existingRegistration.setStatus(registrationDetails.getStatus());
            existingRegistration.setAttended(registrationDetails.isAttended());
            existingRegistration.setFeedback(registrationDetails.getFeedback());
            return registrationRepository.save(existingRegistration);
        });
    }
    
    public boolean deleteRegistration(String id) {
        if (registrationRepository.existsById(id)) {
            registrationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
