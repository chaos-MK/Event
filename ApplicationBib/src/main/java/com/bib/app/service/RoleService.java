package com.bib.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bib.app.entities.Role;
import com.bib.app.repository.RoleRepository;

@Service
public class RoleService {
    
    private RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }
    
    public Optional<Role> getRoleById(String id) {
        return roleRepository.findById(id);
    }
    
    public Iterable<Role> getRolesByName(String name) {
        return roleRepository.findByName(name);
    }
    
    public Iterable<Role> getRolesByRequiredSkill(String skill) {
        return roleRepository.findByRequiredSkillsContaining(skill);
    }
    
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
    
    public Optional<Role> updateRole(String id, Role roleDetails) {
        return roleRepository.findById(id).map(existingRole -> {
            existingRole.setName(roleDetails.getName());
            existingRole.setDescription(roleDetails.getDescription());
            existingRole.setRequiredSkills(roleDetails.getRequiredSkills());
            return roleRepository.save(existingRole);
        });
    }
    
    public boolean deleteRole(String id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

