package com.luanvan.ThesisTrack_Backend.email;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

// ,"http://localhost:4401"
@RestController
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202" })
@RequestMapping("api/v1/email")
public class EmailResource {
    @Autowired
    private EmailService emailService;

    @PostMapping("/calender")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody Email email, @RequestParam String studentName) {
        emailService.sendEmail(email, studentName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mark")
    public ResponseEntity<?> sendMark(@Valid @RequestBody Email email, @RequestParam String studentName) {
        emailService.sendMark(email, studentName);
        return ResponseEntity.ok().build();
    }

}
