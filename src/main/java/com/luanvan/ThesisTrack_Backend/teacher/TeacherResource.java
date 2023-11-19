package com.luanvan.ThesisTrack_Backend.teacher;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// import com.luanvan.ThesisTrack_Backend.teacher.TeacherResponseDTO;

@RestController
@RequestMapping("api/v1/teacher")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4201"})
public class TeacherResource {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<TeacherResponseDTO> getTeacherByNoTeacher(@RequestParam("numberTeacher") String numberTeacher){
        Optional<Teacher> teacher = teacherService.getTeacherByNoTeacher(numberTeacher);

        if(teacher.isPresent()){
            TeacherResponseDTO teacherResponseDTO = new TeacherResponseDTO(
            teacher.get().getId(),
            teacher.get().getNumberTeacher(),
            teacher.get().getName(),
            teacher.get().getAddress(),
            teacher.get().getEmail(),
            teacher.get().getPhone(),
            teacher.get().getMajor(),
            teacher.get().getBirthday(),
            teacher.get().getGender()
            );
            return ResponseEntity.status(HttpStatus.OK).body(teacherResponseDTO);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<Teacher>> getTeachersByFaculty(@PathVariable Integer facultyId) {
        List<Teacher> teachers = teacherService.getTeachersByFaculty(facultyId);
        return ResponseEntity.ok(teachers);
    }
    // public ResponseEntity<List<Teacher>> getTeachersInSameFaculty(@PathVariable Integer facultyId) {
    //     List<Teacher> teachers = teacherService.getTeachersInSameFacultyAsStudent(facultyId);
    //     return ResponseEntity.ok(teachers);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/import")
    public ResponseEntity<String> importTeachers(@RequestParam("file") MultipartFile file) {
        boolean result = teacherService.importTeachersFromExcel(file);
        if (result) {
            return ResponseEntity.ok("Dữ liệu đã được nhập từ tệp Excel.");
        } else {
            return ResponseEntity.badRequest().body("Có lỗi xảy ra khi nhập dữ liệu từ tệp Excel.");
        }
    }

    @GetMapping("/all/faculty/{facultyId}")
    public ResponseEntity<List<Teacher>> getAllTeacher(@PathVariable("facultyId") Integer id) {
        List<Teacher> teachers = teacherService.getAllTeacher(id);
        return ResponseEntity.ok(teachers);
    }


}
