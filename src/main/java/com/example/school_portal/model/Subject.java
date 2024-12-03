package com.example.school_portal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
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
}
