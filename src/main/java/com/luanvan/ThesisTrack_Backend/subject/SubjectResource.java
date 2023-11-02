package com.luanvan.ThesisTrack_Backend.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/subjects")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4401"})
public class SubjectResource {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<String> addSubject(@RequestBody Subject subject) {
        subjectService.addSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body("Subject added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id) {
        Optional<Subject> subject = subjectService.getSubjectById(id);
        return subject.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Subject> getSubjectByCode(@PathVariable String code) {
        Optional<Subject> subject = subjectService.getSubjectByCode(code);
        return subject.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
