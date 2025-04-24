package com.bib.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
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

import com.bib.app.entities.Assignment;
import com.bib.app.service.AssignmentService;

@RestController
@RequestMapping("/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController {
    
    private AssignmentService assignmentService;
    
    @GetMapping
    public ResponseEntity<Iterable<Assignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable String id) {
        return assignmentService.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByEventId(@PathVariable String eventId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByEventId(eventId));
    }
    
    @GetMapping("/staff/{staffId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByStaffId(@PathVariable String staffId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByStaffId(staffId));
    }
    
    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByRoleId(@PathVariable String roleId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByRoleId(roleId));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Assignment>> getAssignmentsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByStatus(status));
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<Assignment>> getAssignmentsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByDateRange(startDate, endDate));
    }
    
    @GetMapping("/event/{eventId}/staff/{staffId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByEventAndStaff(
            @PathVariable String eventId, @PathVariable String staffId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByEventAndStaff(eventId, staffId));
    }
    
    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.createAssignment(assignment));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable String id, @RequestBody Assignment assignmentDetails) {
        return assignmentService.updateAssignment(id, assignmentDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable String id) {
        if (assignmentService.deleteAssignment(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
