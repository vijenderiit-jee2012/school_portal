package com.example.school_portal.model;

import com.example.school_portal.helper.Constants;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student extends User {
    private String name;
    private String email;
    private int mail;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    @JsonBackReference
    private Classroom classroom;

    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    // Constructor
    public Student() {
        setRole(Constants.STUDENT);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMail() {
        return mail;
    }

    public void setMail(int mail) {
        this.mail = mail;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
