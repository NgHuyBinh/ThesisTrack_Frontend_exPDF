package com.luanvan.ThesisTrack_Backend.registerteacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import jakarta.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
@RestController
@RequestMapping("/api/v1/registerteacher")
public class RegisterTeacherResource {
    @Autowired
    private RegisterTeacherService registerTeacherService;

    @PostMapping("/add")
    public ResponseEntity<String> createRegisterTeacher(@RequestBody RegisterTeacherResponseDTO request) {
        try {
            registerTeacherService.createRegisterTeacher(request.getStudentId(), request.getTeacherId(),
                    request.getMark());
            return ResponseEntity.status(HttpStatus.CREATED).body("Thêm đăng ký giảng viên thành công.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // cập nhật status
    // @PatchMapping("/{id}")
    // public ResponseEntity<Void> updateStatus(@PathVariable Integer id, @RequestBody RegisterTeacher registerTeacher) {
    //     registerTeacherService.updateStatus(id, registerTeacher);
    //     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    // }
    // @PostMapping("/add")
    // public ResponseEntity<?> createRegisterTeacher(
    // @RequestBody Integer studentId,
    // @RequestBody Integer teacherId,
    // @RequestBody String mark
    // ) {
    // try {
    // ResponseEntity<String> responseEntity =
    // registerTeacherService.createRegisterTeacher(studentId, teacherId, mark);
    // return responseEntity;
    // } catch (IllegalArgumentException e) {
    // return ResponseEntity.badRequest().body(e.getMessage());
    // }
    // }

    @GetMapping("/all")
    public ResponseEntity<List<RegisterTeacherResponseDTO>> getAllRegisterTeachers() {
        List<RegisterTeacherResponseDTO> registerTeachers = registerTeacherService.getAllRegisterTeachers();
        return ResponseEntity.ok(registerTeachers);
    }

    @GetMapping("/Teacher/{teacherId}")
    public ResponseEntity<List<RegisterTeacherResponseDTO>> getRegisterTeachersByTeacher(
            @PathVariable Integer teacherId) {
        List<RegisterTeacherResponseDTO> registerTeachers = registerTeacherService
                .getRegisterTeachersByTeacher(teacherId);
        return ResponseEntity.ok(registerTeachers);
    }

    // cập nhật trạng thái đăng ký cảu sinh viên 
    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Integer id, @RequestBody RegisterTeacherResponseDTO registerTeacher) {
        registerTeacherService.updateStatus(id, registerTeacher);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // lấy thông tin sinh viên có có giảng viên hướng dẫn có  điều kiện trạng thái  đã đợc duyệt
    @GetMapping("/info")
    public ResponseEntity<List<RegisterTeacherInfo>> getRegisterTeacherInfo() {
        List<RegisterTeacherInfo> registerTeacherInfoList = registerTeacherService.findRegisterTeacherInfoByStatus();
        return ResponseEntity.ok(registerTeacherInfoList);
    }

}
