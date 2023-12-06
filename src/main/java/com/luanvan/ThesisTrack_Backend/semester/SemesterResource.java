 package com.luanvan.ThesisTrack_Backend.semester;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 @RestController
 @RequestMapping("api/v1/semesters")
 @CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202"  })
 public class SemesterResource {

     @Autowired
     private SemesterService semesterService;
    @GetMapping()
    public ResponseEntity<Semester> getCurrentSemester() {
        return ResponseEntity.status(HttpStatus.OK).body(semesterService.getCurrentSemester());
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSemester(@PathVariable Integer id, @RequestBody Semester updatedSemester) {
        semesterService.updateSemester(id, updatedSemester);
        return ResponseEntity.ok().build();
    }

 }
