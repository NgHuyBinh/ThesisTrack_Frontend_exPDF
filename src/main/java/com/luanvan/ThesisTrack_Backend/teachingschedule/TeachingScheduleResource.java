// package com.luanvan.ThesisTrack_Backend.teachingschedule;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/teachingschedule")
// @CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202" })

// public class TeachingScheduleResource {

//     @Autowired
//     private TeachingScheduleService teachingScheduleService;

//     @PostMapping("/add")
//     public ResponseEntity<String> addTeachingSchedule(@RequestBody TeachingSchedule teachingSchedule) {
//         teachingScheduleService.addTeachingSchedule (teachingSchedule);
//         return ResponseEntity.status(HttpStatus.CREATED).build();
//     }

//     @GetMapping("/all")
//     public ResponseEntity<List<TeachingSchedule>> getAllTeachingSchedules() {
//         List<TeachingSchedule> teachingSchedules = teachingScheduleService.getAllTeachingSchedules();
//         return ResponseEntity.ok(teachingSchedules);
//     }

//     @GetMapping("/by-calender/{calenderId}")
//     public ResponseEntity<List<TeachingSchedule>> getTeachingSchedulesByCalenderId(@PathVariable Integer calenderId) {
//         List<TeachingSchedule> teachingSchedules = teachingScheduleService.getTeachingSchedulesByCalenderId(calenderId);
//         return ResponseEntity.ok(teachingSchedules);
//     }

//     @GetMapping("/by-addgroupstudent/{addGroupStudentId}")
//     public ResponseEntity<List<TeachingSchedule>> getTeachingSchedulesByAddGroupStudentId(@PathVariable Integer addGroupStudentId) {
//         List<TeachingSchedule> teachingSchedules = teachingScheduleService.getTeachingSchedulesByAddGroupStudentId(addGroupStudentId);
//         return ResponseEntity.ok(teachingSchedules);
//     }

  
// }