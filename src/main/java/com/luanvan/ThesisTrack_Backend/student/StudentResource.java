package com.luanvan.ThesisTrack_Backend.student;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/student")
// , "http://localhost:4201"
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
public class StudentResource {
    
    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<StudentResponseDTO> getStudentByNoStudent(@RequestParam("numberStudent") String numberStudent) {
       
//        System.out.print(numberStudent);
        Optional<Student> student = studentService.getStudentByNoStudent(numberStudent);
        
        if(student.isPresent()) {
            StudentResponseDTO studentResponseDTO = new StudentResponseDTO(
            student.get().getId(),
            student.get().getNumberStudent(),
            student.get().getName(),
            student.get().getAddress(),
            student.get().getEmail(),
            student.get().getPhone(),
            student.get().getMajor(),
            student.get().getBirthday(),
            student.get().getGender(),
            student.get().getClassroom(),
            student.get().getTeacher(),
            student.get().getStatus()
            ) ;

            return ResponseEntity.status(HttpStatus.OK).body(studentResponseDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{studentId}/facultyId")
    public ResponseEntity<Integer> getFacultyIdByStudentId(@PathVariable Integer studentId) {
        Integer facultyId = studentService.getFacultyIdByStudentId(studentId);

        if (facultyId != null) {
            return ResponseEntity.status(HttpStatus.OK).body(facultyId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    
}   
