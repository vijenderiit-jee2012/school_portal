package com.example.school_portal.service;

import com.example.school_portal.model.Classroom;
import com.example.school_portal.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    // Create or Update a Classroom
    public Classroom saveClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    // Get all Classrooms
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    // Get Classroom by ID
    public Optional<Classroom> getClassroomById(Long id) {
        return classroomRepository.findById(id);
    }

    // Delete Classroom by ID
    public void deleteClassroom(Long id) {
        classroomRepository.deleteById(id);
    }
}

