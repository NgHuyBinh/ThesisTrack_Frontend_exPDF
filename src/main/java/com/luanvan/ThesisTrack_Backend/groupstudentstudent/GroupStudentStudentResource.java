package com.luanvan.ThesisTrack_Backend.groupstudentstudent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.ThesisTrack_Backend.calenderthesis.CalenderThesis;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/group-student-student")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202"  })
public class GroupStudentStudentResource {
    @Autowired
    private GroupStudentStudentService service;

    @PostMapping("/add")
    public ResponseEntity<Void> addGroupStudentStudent(@RequestBody @Valid GroupStudentStudent groupStudentStudent) {
        service.addGroupStudentStudent(groupStudentStudent);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
     @GetMapping("group-student-id/{id}")
    public ResponseEntity<List<GroupStudentStudent>> getByGroupStudentId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getByGroupStudentId(id));
    }
}
