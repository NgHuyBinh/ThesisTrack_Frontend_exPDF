package com.luanvan.ThesisTrack_Backend.markteacher;

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

import com.luanvan.ThesisTrack_Backend.semester.SemesterService;
import com.luanvan.ThesisTrack_Backend.student.StudentService;
import com.luanvan.ThesisTrack_Backend.teacher.TeacherService;

import java.util.List;

@RestController
@RequestMapping("api/v1/marks")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
public class MarkResource {

    private final MarkService markService;
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final SemesterService semesterService;

    @Autowired
    public MarkResource(MarkService markService
    , TeacherService teacherService
    , StudentService studentService
    , SemesterService semesterService) {
        this.markService = markService;
        this.teacherService =  teacherService;
        this.studentService = studentService;
        this.semesterService = semesterService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Mark>> getAllMarks() {
        List<Mark> marks = markService.getAllMarks();
        return ResponseEntity.ok(marks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mark> getMarkById(@PathVariable Integer id) {
        Mark mark = markService.getMarkById(id);
        return mark != null ? ResponseEntity.ok(mark) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<Mark> createMark(@RequestBody Mark mark) {
        markService.createMark(mark);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // @PatchMapping("/{id}")
    // public ResponseEntity<Mark> updateMark(@PathVariable Integer id, @RequestBody
    // Mark updatedMark) {
    // Mark updated = markService.updateMark(id, updatedMark);
    // return updated != null ? ResponseEntity.ok(updated) :
    // ResponseEntity.notFound().build();
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMark(@PathVariable Integer id) {
        markService.deleteMark(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Mark>> getMarksByTeacherId(@PathVariable Integer teacherId) {
        List<Mark> marks = markService.getMarksByTeacherId(teacherId);
        return ResponseEntity.ok(marks);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Mark>> getMarksByStudentId(@PathVariable Integer studentId) {
        List<Mark> marks = markService.getMarksByStudentId(studentId);
        return ResponseEntity.ok(marks);
    }

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<List<Mark>> getMarksBySemesterId(@PathVariable Integer semesterId) {
        List<Mark> marks = markService.getMarksBySemesterId(semesterId);
        return ResponseEntity.ok(marks);
    }

}