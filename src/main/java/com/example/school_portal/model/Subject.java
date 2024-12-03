package com.example.school_portal.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Many-to-Many relationship with Students
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;

    // Many-to-Many relationship with Classrooms
    @ManyToMany(mappedBy = "subjects")
    private List<Classroom> classrooms;

    // Many-to-Many relationship with Teachers
    @ManyToMany(mappedBy = "subjects")
    private List<Teacher> teachers;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(List<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
