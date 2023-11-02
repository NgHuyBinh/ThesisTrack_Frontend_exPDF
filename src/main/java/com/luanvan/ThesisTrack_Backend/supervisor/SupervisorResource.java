// package com.luanvan.ThesisTrack_Backend.supervisor;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/supervisor")
// public class SupervisorResource {
//     private final SupervisorService supervisorService;

//     @Autowired
//     public SupervisorResource(SupervisorService supervisorService) {
//         this.supervisorService = supervisorService;
//     }

//     // tạo hội đồng báo cáo luận văn 
//     @PostMapping("/create")
//     public ResponseEntity<SupervisorResponseDTO> createSupervisor(@RequestBody SupervisorResponseDTO supervisorResponseDTO) {
//         Supervisor supervisor = supervisorService.createSupervisor(supervisorResponseDTO);
//         // SupervisorResponseDTO SupervisorResponseDTO = convertToResponseDTO(supervisor);
//         convertToResponseDTO(supervisor);
//         return ResponseEntity.ok(supervisorResponseDTO);
//     }
    

//     private SupervisorResponseDTO convertToResponseDTO(Supervisor supervisor) {
//         return null;
//     }

//     @GetMapping("/all")
//     public ResponseEntity<List<SupervisorResponseDTO>> getAllSupervisors() {
//         List<SupervisorResponseDTO> supervisors = supervisorService.getAllSupervisors();
//         return ResponseEntity.ok(supervisors);
//     }

//     @GetMapping("/byTeacher/{teacherId}")
//     public ResponseEntity<List<SupervisorResponseDTO>> getSupervisorsByTeacherId(@PathVariable Integer teacherId) {
//         List<SupervisorResponseDTO> supervisors = supervisorService.getSupervisorsByTeacherId(teacherId);
//         return ResponseEntity.ok(supervisors);
//     }
// }
