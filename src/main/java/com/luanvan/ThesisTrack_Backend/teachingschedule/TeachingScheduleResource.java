package com.luanvan.ThesisTrack_Backend.teachingschedule;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luanvan.ThesisTrack_Backend.exception.InvalidValueException;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachingschedule")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })

public class TeachingScheduleResource {

    private final TeachingScheduleService teachingScheduleService;

    public TeachingScheduleResource(TeachingScheduleService teachingScheduleService) {
        this.teachingScheduleService = teachingScheduleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeachingSchedule(@RequestBody TeachingScheduleResponseDTO requestDTO) {
        try {
            // System.out.println("22342342342343242342342342342343242342342334234232344234242342");
            teachingScheduleService.addTeachingSchedule(requestDTO);
            return new ResponseEntity<>("Lịch báo cáo của nhóm này được thêm thành công.", HttpStatus.CREATED);
        } catch (InvalidValueException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeachingScheduleResponseDTO>> getAllTeachingSchedules() {
        List<TeachingScheduleResponseDTO> teachingSchedules = teachingScheduleService.getAllTeachingSchedules();
        return new ResponseEntity<>(teachingSchedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeachingScheduleResponseDTO> getTeachingScheduleById(@PathVariable Integer id) {
        TeachingScheduleResponseDTO teachingSchedule = teachingScheduleService.getTeachingScheduleById(id);
        if (teachingSchedule != null) {
            return new ResponseEntity<>(teachingSchedule, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/groupstudent/{groupStudentId}")
    public ResponseEntity<List<TeachingScheduleResponseDTO>> getTeachingSchedulesByGroupStudent(
            @PathVariable Integer groupStudentId) {
        List<TeachingScheduleResponseDTO> teachingSchedules = teachingScheduleService
                .getAllTeachingSchedulesByGroupStudent(groupStudentId);
        return new ResponseEntity<>(teachingSchedules, HttpStatus.OK);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TeachingScheduleResponseDTO>> getTeachingScheduleByTeacherId(
            @PathVariable Integer teacherId) {
        List<TeachingScheduleResponseDTO> teachingSchedule = teachingScheduleService
                .getTeachingScheduleByTeacherId(teacherId);
        return new ResponseEntity<>(teachingSchedule, HttpStatus.OK);
    }

    // chỉnh sửa lịch báo cáo của nhóm sinh viên 
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateTeachingSchedule(@PathVariable Integer id, @RequestBody TeachingScheduleResponseDTO requestDTO) {
        try {
            teachingScheduleService.updateTeachingScheduleById(id, requestDTO);
            return new ResponseEntity<>("Lịch báo cáo được cập nhật thành công.", HttpStatus.OK);
        } catch (InvalidValueException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // lấy thông tin theo id giảng viên
    // @GetMapping("/teacher/{teacherId}")
    // public ResponseEntity<List<TeachingScheduleResponseDTO>>
    // getTeachingSchedulesByTeacherId(
    // @PathVariable Integer teacherId) {
    // List<TeachingScheduleResponseDTO> teachingSchedules = teachingScheduleService
    // .getAllTeachingSchedulesByTeacherId(teacherId);
    // return new ResponseEntity<>(teachingSchedules, HttpStatus.OK);
    // }
    // @GetMapping("/calender/{calenderId}")
    // public ResponseEntity<List<TeachingScheduleResponseDTO>>
    // getTeachingSchedulesByCalender(
    // @PathVariable Integer calenderId) {
    // List<TeachingScheduleResponseDTO> teachingSchedules = teachingScheduleService
    // .getTeachingSchedulesByCalender(calenderId);
    // return new ResponseEntity<>(teachingSchedules, HttpStatus.OK);
    // }

    // @GetMapping("/semester/{semesterId}")
    // public ResponseEntity<List<TeachingScheduleResponseDTO>>
    // getTeachingSchedulesBySemester(
    // @PathVariable Integer semesterId) {
    // List<TeachingScheduleResponseDTO> teachingSchedules = teachingScheduleService
    // .getTeachingSchedulesBySemester(semesterId);
    // return new ResponseEntity<>(teachingSchedules, HttpStatus.OK);
    // }

}