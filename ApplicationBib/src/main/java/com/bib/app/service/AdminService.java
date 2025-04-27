package com.bib.app.service;

import com.bib.app.entities.Admin;
import com.bib.app.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

   // @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return (List<Admin>) adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(String id) {
        return adminRepository.findById(id);
    }

    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public Admin updateAdmin(String id, Admin adminDetails) {
        if (adminRepository.existsById(id)) {
            adminDetails.setId(id);
            return adminRepository.save(adminDetails);
        }
        return null; // Handle better in a real-world application
    }

    public void deleteAdmin(String id) {
        adminRepository.deleteById(id);
    }
}