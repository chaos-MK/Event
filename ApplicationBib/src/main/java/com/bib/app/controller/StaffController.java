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
import org.springframework.web.bind.annotation.RestController;

import com.bib.app.entities.Staff;
import com.bib.app.service.StaffService;

@RestController
@RequestMapping("/staff")
@CrossOrigin(origins = "*")
public class StaffController {
    
    private StaffService staffService;
    
    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable String id) {
        return staffService.getStaffById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Staff> getStaffByEmail(@PathVariable String email) {
        return staffService.getStaffByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/skill/{skill}")
    public ResponseEntity<Iterable<Staff>> getStaffBySkill(@PathVariable String skill) {
        return ResponseEntity.ok(staffService.getStaffBySkill(skill));
    }
    
    @GetMapping("/position/{position}")
    public ResponseEntity<Iterable<Staff>> getStaffByPosition(@PathVariable String position) {
        return ResponseEntity.ok(staffService.getStaffByPosition(position));
    }
    
    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        return ResponseEntity.status(HttpStatus.CREATED).body(staffService.createStaff(staff));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable String id, @RequestBody Staff staffDetails) {
        return staffService.updateStaff(id, staffDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable String id) {
        if (staffService.deleteStaff(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
