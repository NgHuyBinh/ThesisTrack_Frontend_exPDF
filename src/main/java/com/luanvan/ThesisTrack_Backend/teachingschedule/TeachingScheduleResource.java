//  package com.luanvan.ThesisTrack_Backend.teachingschedule;

//  import org.springframework.beans.factory.annotation.Autowired;
//  import org.springframework.http.HttpStatus;
//  import org.springframework.http.ResponseEntity;
//  import org.springframework.web.bind.annotation.*;

//  import java.util.List;


//  @CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
//  @RestController
//  @RequestMapping("api/v1/teachingschedule")
//  public class TeachingScheduleResource {
//      private final TeachingScheduleService teachingScheduleService;

//      @Autowired
//      public TeachingScheduleResource(TeachingScheduleService teachingScheduleService) {
//          this.teachingScheduleService = teachingScheduleService;
//      }

//      @PostMapping("/add")
//      public ResponseEntity<TeachingSchedule> createTeachingSchedule(@RequestBody TeachingSchedule teachingSchedule) {
//          TeachingSchedule createdSchedule = teachingScheduleService.createTeachingSchedule(teachingSchedule);
//          if (createdSchedule == null) {
//              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//          }
//          return ResponseEntity.status(HttpStatus.CREATED).body(createdSchedule);
//      }

//      @GetMapping("/byGroupStudent/{groupStudentId}")
//      public ResponseEntity<List<TeachingSchedule>> getTeachingSchedulesByGroupStudentId(@PathVariable Integer groupStudentId) {
//          List<TeachingSchedule> teachingSchedules = teachingScheduleService.getTeachingSchedulesByGroupStudentId(groupStudentId);
//          return ResponseEntity.ok(teachingSchedules);
//      }

//      @GetMapping("/all")
//      public ResponseEntity<List<TeachingScheduleResponseDTO>> getAllTeachingSchedules() {
//          List<TeachingScheduleResponseDTO> schedules = teachingScheduleService.getAllTeachingSchedulesWithoutId();
//          return ResponseEntity.ok(schedules);
//      }


//  }
