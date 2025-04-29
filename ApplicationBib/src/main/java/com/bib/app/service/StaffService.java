package com.bib.app.service;

import com.bib.app.entities.Staff;
import com.bib.app.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public StaffService(StaffRepository staffRepository, 
                       RedisTemplate<String, Object> redisTemplate) {
        this.staffRepository = staffRepository;
        this.redisTemplate = redisTemplate;
    }

    public Staff createStaff(Staff staff) {
        Staff saved = staffRepository.save(staff);
        updateAvailabilityIndex(saved);
        updateSkillsIndex(saved);
        return saved;
    }

    public List<Staff> getAllStaff() {
        return (List<Staff>) staffRepository.findAll();
    }

    public Optional<Staff> getStaffById(String id) {
        return staffRepository.findById(id);
    }

    public List<Staff> getAvailableStaff() {
        return staffRepository.findByAvailability(true);
    }

    public Staff updateStaff(String id, Staff staffDetails) {
        return staffRepository.findById(id)
            .map(existing -> {
                existing.setFirstname(staffDetails.getFirstname());
                existing.setLastname(staffDetails.getLastname());
                existing.setEmail(staffDetails.getEmail());
                existing.setPhone(staffDetails.getPhone());
                existing.setPosition(staffDetails.getPosition());
                existing.setAvailability(staffDetails.getAvailability());
                existing.setSkills(staffDetails.getSkills());
                
                Staff updated = staffRepository.save(existing);
                updateAvailabilityIndex(updated);
                updateSkillsIndex(updated);
                return updated;
            })
            .orElse(null);
    }

    private void updateAvailabilityIndex(Staff staff) {
        String key = "staff:available";
        if (Boolean.TRUE.equals(staff.getAvailability())) {
            redisTemplate.opsForSet().add(key, staff.getStaffId());
        } else {
            redisTemplate.opsForSet().remove(key, staff.getStaffId());
        }
    }

    private void updateSkillsIndex(Staff staff) {
        staff.getSkills().forEach(skill -> {
            String key = "skill:" + skill + ":staff";
            redisTemplate.opsForSet().add(key, staff.getStaffId());
        });
    }

    public void deleteStaff(String id) {
        staffRepository.deleteById(id);
    }
}