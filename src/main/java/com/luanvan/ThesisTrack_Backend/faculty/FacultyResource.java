package com.luanvan.ThesisTrack_Backend.faculty;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/faculties")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
public class FacultyResource {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAllFaculties();
        return ResponseEntity.ok(faculties);
    }

    // @PostMapping
    // public ResponseEntity<String> addFaculty(@RequestBody Faculty faculty) {
    // facultyService.addFaculty(faculty);
    // return ResponseEntity.status(HttpStatus.CREATED).body("Faculty added
    // successfully");
    // }
}

