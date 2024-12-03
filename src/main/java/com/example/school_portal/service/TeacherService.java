package com.example.school_portal.service;

import com.example.school_portal.model.Teacher;
import com.example.school_portal.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Create or Update a Teacher
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Get all Teachers
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Get Teacher by ID
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    // Delete Teacher by ID
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
