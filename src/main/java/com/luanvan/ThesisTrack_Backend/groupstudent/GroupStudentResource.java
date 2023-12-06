package com.luanvan.ThesisTrack_Backend.groupstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/groupstudents")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
public class GroupStudentResource {

    @Autowired
    private GroupStudentService groupStudentService;

    @GetMapping("/all")
    public ResponseEntity<List<GroupStudent>> getAllGroupStudents() {
        List<GroupStudent> groupStudents = groupStudentService.getAllGroupStudents();
        return new ResponseEntity<>(groupStudents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupStudent> getGroupStudentById(@PathVariable Integer id) {
        Optional<GroupStudent> groupStudent = groupStudentService.getGroupStudentById(id);
        return groupStudent.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // lấy thông nhóm sinh viên theo id giảng viên
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<GroupStudent>> getGroupStudentsByTeacherId(@PathVariable Integer teacherId) {
        List<GroupStudent> groupStudents = groupStudentService.getGroupStudentsByTeacherId(teacherId);
        return new ResponseEntity<>(groupStudents, HttpStatus.OK);
    }

    // tạo nhóm sinh viên báo cáo 
    @PostMapping("/add")
    public ResponseEntity<Void> addGroupStudent(@RequestBody GroupStudent groupStudent) {
        groupStudentService.addGroupStudent(groupStudent);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    // xóa nhóm sinh viên 
    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer groupId) {
        groupStudentService.deleteGroup(groupId);
        return ResponseEntity.noContent().build();
    }

}