package com.luanvan.ThesisTrack_Backend.student;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> getStudentByNoStudent(String noStudent) {
        return studentRepository.findByNumberStudent(noStudent);
    }

    public boolean existsById(Integer studentId) {
        return false;
    }

    public Student findById(Integer studentId) {
        return null;
    }

    // public Student findById(Integer studentId) {
    //     return null;
    // }

}
 