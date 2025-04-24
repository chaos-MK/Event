package com.bib.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bib.app.entities.Assignment;

@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, String> {
    
    List<Assignment> findByEventId(String eventId);
    
    List<Assignment> findByStaffId(String staffId);
    
    List<Assignment> findByRoleId(String roleId);
    
    List<Assignment> findByStatus(String status);
    
    List<Assignment> findByStartTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<Assignment> findByEventIdAndStaffId(String eventId, String staffId);
}

