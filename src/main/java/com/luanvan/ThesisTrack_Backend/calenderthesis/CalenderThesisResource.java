package com.luanvan.ThesisTrack_Backend.calenderthesis;

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

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/calenderthesis")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202" })

public class CalenderThesisResource {
    @Autowired
    private CalenderThesisService calenderThesisService;

    @PostMapping("/add")
    public ResponseEntity<Void> addCalenerThesis(@RequestBody @Valid CalenderThesis calenderThesis) {
        calenderThesisService.addCalenerThesis(calenderThesis);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("group-student-id/{id}")
    public ResponseEntity<CalenderThesis> getByGroupStudentId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(calenderThesisService.getByGroupStudentId(id));
    }
    @GetMapping("teacher/{id}")
    public ResponseEntity<List<CalenderThesis>> getByTeacherId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(calenderThesisService.getByTeacherId(id));
    } 
}
