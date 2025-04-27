package com.bib.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bib.app.entities.Staff;
import com.bib.app.repository.StaffRepository;

@Service
public class StaffService {
    
    private StaffRepository staffRepository;
    
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        staffRepository.findAll().forEach(staffList::add);
        return staffList;
    }
    
    public Optional<Staff> getStaffById(String id) {
        return staffRepository.findById(id);
    }
    
    public Optional<Staff> getStaffByEmail(String email) {
        return staffRepository.findByEmail(email);
    }
    
    public Iterable<Staff> getStaffBySkill(String skill) {
        return staffRepository.findBySkillsContaining(skill);
    }
    
    public Iterable<Staff> getStaffByPosition(String position) {
        return staffRepository.findByPosition(position);
    }
    
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }
    
    public Optional<Staff> updateStaff(String id, Staff staffDetails) {
        return staffRepository.findById(id).map(existingStaff -> {
            existingStaff.setFirstname(staffDetails.getFirstname());
            existingStaff.setLastname(staffDetails.getLastname());
            existingStaff.setEmail(staffDetails.getEmail());
            existingStaff.setPhone(staffDetails.getPhone());
            existingStaff.setPosition(staffDetails.getPosition());
            existingStaff.setSkills(staffDetails.getSkills());
            return staffRepository.save(existingStaff);
        });
    }
    
    public boolean deleteStaff(String id) {
        if (staffRepository.existsById(id)) {
            staffRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
