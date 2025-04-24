package com.bib.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bib.app.entities.Assignment;
import com.bib.app.repository.AssignmentRepository;

@Service
public class AssignmentService {
    
    private AssignmentRepository assignmentRepository;
    
    public Iterable<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }
    
    public Optional<Assignment> getAssignmentById(String id) {
        return assignmentRepository.findById(id);
    }
    
    public List<Assignment> getAssignmentsByEventId(String eventId) {
        return assignmentRepository.findByEventId(eventId);
    }
    
    public List<Assignment> getAssignmentsByStaffId(String staffId) {
        return assignmentRepository.findByStaffId(staffId);
    }
    
    public List<Assignment> getAssignmentsByRoleId(String roleId) {
        return assignmentRepository.findByRoleId(roleId);
    }
    
    public List<Assignment> getAssignmentsByStatus(String status) {
        return assignmentRepository.findByStatus(status);
    }
    
    public List<Assignment> getAssignmentsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return assignmentRepository.findByStartTimeBetween(startDate, endDate);
    }
    
    public List<Assignment> getAssignmentsByEventAndStaff(String eventId, String staffId) {
        return assignmentRepository.findByEventIdAndStaffId(eventId, staffId);
    }
    
    public Assignment createAssignment(Assignment assignment) {
        assignment.setCreatedAt(LocalDateTime.now());
        assignment.setUpdatedAt(LocalDateTime.now());
        return assignmentRepository.save(assignment);
    }
    
    public Optional<Assignment> updateAssignment(String id, Assignment assignmentDetails) {
        return assignmentRepository.findById(id).map(existingAssignment -> {
            existingAssignment.setEventId(assignmentDetails.getEventId());
            existingAssignment.setStaffId(assignmentDetails.getStaffId());
            existingAssignment.setRoleId(assignmentDetails.getRoleId());
            existingAssignment.setStartTime(assignmentDetails.getStartTime());
            existingAssignment.setEndTime(assignmentDetails.getEndTime());
            existingAssignment.setStatus(assignmentDetails.getStatus());
            existingAssignment.setNotes(assignmentDetails.getNotes());
            existingAssignment.setUpdatedAt(LocalDateTime.now());
            return assignmentRepository.save(existingAssignment);
        });
    }
    
    public boolean deleteAssignment(String id) {
        if (assignmentRepository.existsById(id)) {
            assignmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

