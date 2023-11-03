package com.luanvan.ThesisTrack_Backend.student;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.faculty.Faculty;

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

    public Integer getFacultyIdByStudentId(Integer studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            Faculty faculty = student.getFaculty();

            if (faculty != null) {
                return faculty.getId();
            }
        }

        return null; // Hoặc có thể trả về một giá trị mặc định khác tùy theo yêu cầu của bạn.
    }
}
 