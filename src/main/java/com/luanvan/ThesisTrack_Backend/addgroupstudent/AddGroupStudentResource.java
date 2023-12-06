package com.luanvan.ThesisTrack_Backend.addgroupstudent;

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


@RestController
@RequestMapping("/api/v1/addgroupstudent")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202" })

public class AddGroupStudentResource {
    private final AddGroupStudentService addGroupStudentService;

    @Autowired
    public AddGroupStudentResource(AddGroupStudentService addGroupStudentService) {
        this.addGroupStudentService = addGroupStudentService;
    }

    // chia nhóm cho sinh viên
    @PostMapping("/add")
    public ResponseEntity<Void> createAddGroupStudent(@RequestBody AddGroupStudent addGroupStudent) {
        addGroupStudentService.createAddGroupStudent(addGroupStudent);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    // Lấy tất cả mục chia nhóm sinh viên
    @GetMapping("/all")
    public ResponseEntity<List<AddGroupStudent>> getAllAddGroupStudents() {
        List<AddGroupStudent> addGroupStudents = addGroupStudentService.getAllAddGroupStudents();
        return ResponseEntity.ok(addGroupStudents);
    }


    // Lấy danh sách chia nhóm sinh viên theo ID nhóm sinh viên
    @GetMapping("/groupstudent/{groupStudentId}")
    public ResponseEntity<List<AddGroupStudent>> getAddGroupStudentsByGroupStudent(@PathVariable Integer groupStudentId) {
        List<AddGroupStudent> addGroupStudents = addGroupStudentService.getAddGroupStudentsByGroupStudent(groupStudentId);
        return ResponseEntity.ok(addGroupStudents);
    }

    // Lấy một mục chia nhóm sinh viên dựa trên ID sinh viên
    @GetMapping("/student/{studentId}")
    public ResponseEntity<AddGroupStudent> getAddGroupStudentByStudent(@PathVariable Integer studentId) {
        AddGroupStudent addGroupStudent = addGroupStudentService.getAddGroupStudentByStudent(studentId);
        return ResponseEntity.ok(addGroupStudent);
    }


}
