package com.example.school_portal.service;

import com.example.school_portal.model.Subject;
import com.example.school_portal.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    // Create or Update a Subject
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    // Get all Subjects
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    // Get Subject by ID
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    // Delete Subject by ID
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }


}