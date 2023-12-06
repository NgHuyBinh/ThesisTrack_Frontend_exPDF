package com.luanvan.ThesisTrack_Backend.registertopic;

import java.util.*;
// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202"  })
@RestController
@RequestMapping("api/v1/registertopic")
public class RegisiterResource {
    @Autowired
    private RegisterService registerService;

    @PostMapping()
    public ResponseEntity<?> registerTopic(@Valid @RequestBody RegisterTopic registerTopic) {
        registerService.registerTopic(registerTopic);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // @GetMapping("/teacher/{teacherId}")
    // public ResponseEntity<List<RegisterTopic>>
    // getRegisterTopicsByTeacher(@PathVariable Integer teacherId) {
    // List<RegisterTopic> registerTopics =
    // registerService.getRegisterTopicsByTeacherId(teacherId);
    // return ResponseEntity.status(HttpStatus.OK).body(registerTopics);
    // }
    @GetMapping("teacher/{id}")
    public ResponseEntity<List<RegisterTopic>> getAllTopics(@PathVariable("id") Integer teacherId) {
        List<RegisterTopic> registerTopics = registerService.getAllTopics(teacherId);
        return ResponseEntity.status(HttpStatus.OK).body(registerTopics);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Integer id, @RequestBody RegisterTopic registerTopic) {
        registerService.updateStatus(id, registerTopic);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegisterTopic>> getAllRegisters() {
        List<RegisterTopic> registerTopicList = registerService.getAllRegister();
        return new  ResponseEntity<>(registerTopicList, HttpStatus.OK);
    }
    @GetMapping("student/{studentId}")
    public ResponseEntity<RegisterTopic> getByStudentId(@PathVariable("studentId") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(registerService.getByStudentId(id));
    }
}