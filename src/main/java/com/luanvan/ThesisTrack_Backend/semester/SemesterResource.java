 package com.luanvan.ThesisTrack_Backend.semester;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;

 @RestController
 @RequestMapping("api/v1/semesters")
 public class SemesterResource {

     @Autowired
     private SemesterService semesterService;


 }
