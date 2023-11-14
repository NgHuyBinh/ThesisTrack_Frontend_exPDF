package com.luanvan.ThesisTrack_Backend.supervisor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supervisor")

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
public class SupervisorResource {
//    private final SupervisorService supervisorService;
//
//    @Autowired
//    public SupervisorResource(SupervisorService supervisorService) {
//        this.supervisorService = supervisorService;
//    }

    // tạo hội đồng báo cáo luận văn 
    // @PostMapping("/add")
    // public ResponseEntity<Void> createSupervisor(@RequestBody Supervisor supervisor) {
    //     supervisorService.createSupervisor(supervisor);
    //     return ResponseEntity.status(HttpStatus.CREATED).build();
    // }
    

}
