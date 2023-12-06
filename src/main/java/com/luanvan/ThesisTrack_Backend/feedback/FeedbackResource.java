package com.luanvan.ThesisTrack_Backend.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedbacks")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202"  })
public class FeedbackResource {

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackResource(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // lấy tất cả phản hồi
    @GetMapping("/all")
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    // lấy phản hồi theo id
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Integer id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return feedback != null ? ResponseEntity.ok(feedback) : ResponseEntity.notFound().build();
    }

    // lấy phản hồi theo sinh viên
    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getFeedbacksByStudentId(@PathVariable Integer studentId) {
        try {
            List<Feedback> feedbacks = feedbackService.getFeedbacksByStudentId(studentId);
            return ResponseEntity.ok(feedbacks);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // thêm
    @PostMapping("/add")
    public ResponseEntity<Void> createFeedback(@RequestBody Feedback feedback) {
        feedbackService.createFeedback(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // @PostMapping("/add")
    // public ResponseEntity<?> createFeedback(@RequestBody Feedback feedback) {
    // try {
    // feedbackService.createFeedback(feedback);
    // return ResponseEntity.status(HttpStatus.CREATED).build();
    // } catch (IllegalArgumentException e) {
    // // Xử lý lỗi khi kiểm tra dữ liệu không hợp lệ
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    // }
    // }

    // chỉnh sửa , có thể ko cần chỉnh sửa
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateFeedback(@PathVariable Integer id, @RequestBody Feedback updatedFeedback) {
        String resultMessage = feedbackService.updateFeedback(id, updatedFeedback);
        return ResponseEntity.ok(resultMessage);
    }
    // @PatchMapping("/{id}")
    // public ResponseEntity<Feedback> updateFeedback(@PathVariable Integer id,
    // @RequestBody Feedback updatedFeedback) {
    // Feedback updated = feedbackService.updateFeedback(id, updatedFeedback);
    // return updated != null ? ResponseEntity.ok(updated) :
    // ResponseEntity.notFound().build();
    // }

    // xóa phản hồi
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Integer id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
