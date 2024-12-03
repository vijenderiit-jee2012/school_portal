package com.example.school_portal.service;

import com.example.school_portal.helper.Constants;
import com.example.school_portal.model.*;
import com.example.school_portal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public boolean assignStudentToClassroom(String username, Long studentId, Long classroomId) throws Exception {
        User user = userRepository.findByUsername(username);
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<Classroom> classroomOpt = classroomRepository.findById(classroomId);

        if (studentOpt.isEmpty() || classroomOpt.isEmpty()) {
            throw new Exception("Student or Classroom not found");
        }

        Student student = studentOpt.get();
        Classroom classroom = classroomOpt.get();

        if (user.getRole().equals(Constants.ADMIN)) {
            student.setClassroom(classroom);
            studentRepository.save(student);
        }
        else if (user.getRole().equals(Constants.TEACHER)){
            Optional<Teacher> teacherOpt = teacherRepository.findById(user.getId());
            if (teacherOpt.isPresent() && teacherOpt.get().getClassrooms().contains(classroom)) {
                student.setClassroom(classroom);
                studentRepository.save(student);
            }
        } else{
            return false;
        }
        return true;
    }
}
