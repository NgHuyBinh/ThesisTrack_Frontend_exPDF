package com.luanvan.ThesisTrack_Backend.groupstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/groupstudents")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202" })
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
    // @PostMapping("/add")
    // public ResponseEntity<Void> addGroupStudent(@RequestBody GroupStudent groupStudent) {
    //     groupStudentService.addGroupStudent(groupStudent);
    //     return ResponseEntity.status(HttpStatus.CREATED).build();
    // }
    @PostMapping("/add")
    public ResponseEntity<GroupStudent> addGroupStudent(@RequestBody GroupStudent groupStudent) {
        GroupStudent addedGroupStudent = groupStudentService.addGroupStudent(groupStudent);
        return new ResponseEntity<>(addedGroupStudent, HttpStatus.CREATED);
    }
    
    // xóa nhóm sinh viên 
    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer groupId) {
        groupStudentService.deleteGroup(groupId);
        return ResponseEntity.noContent().build();
    }

    // chỉnh sửa nhóm sinh viên
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateGroup(@PathVariable Integer id, @RequestBody GroupStudent update){
        String resultMessage = groupStudentService.updateGroups(id, update);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @PostMapping("addgroup")
    // @PostMapping("/add-request")
    // public ResponseEntity<Void> addGroupStudent(@RequestBody GroupStudentRequestDTO requestDTO) {
    //     groupStudentService.addGroupStudent(requestDTO);
    //     return  ResponseEntity.status(HttpStatus.CREATED).build();
    // }

}