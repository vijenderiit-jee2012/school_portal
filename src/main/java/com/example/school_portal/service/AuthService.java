package com.example.school_portal.service;


import com.example.school_portal.repository.AdminRepository;
import com.example.school_portal.repository.StudentRepository;
import com.example.school_portal.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Object findUserByUsername(String username, String role) {
        return null;
    }
}