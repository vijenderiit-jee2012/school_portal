package com.example.school_portal.service;


import com.example.school_portal.model.Admin;
import com.example.school_portal.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerAdmin(Admin admin) {
        Optional<Admin> adminOpt = adminRepository.findByUserName(admin.getUserName());
        if (adminOpt.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        adminRepository.save(admin);
    }

    public Admin authenticateUser(String username, String password) {
        Optional<Admin> adminOpt = adminRepository.findByUserName(username);
        if (adminOpt.isEmpty()) {
            return null;
        }
        Admin admin = adminOpt.get();
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            return null;
        }
        return admin;
    }
}