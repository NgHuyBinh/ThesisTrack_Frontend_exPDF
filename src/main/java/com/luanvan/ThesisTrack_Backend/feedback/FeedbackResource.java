// package com.luanvan.ThesisTrack_Backend.feedback;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/feedback")
// public class FeedbackResource {

    
    
//     private final FeedbackService feedbackService;

//     public FeedbackResource(FeedbackService feedbackService) {
//         this.feedbackService = feedbackService;
//     }

//     @PostMapping("/add")
//     public void addFeedback(@RequestBody FeedbackResponseDTO feedbackResponseDTO) {
//         feedbackService.addFeedback(feedbackResponseDTO);
//     }

//     @GetMapping("/all")
//     public List<FeedbackResponseDTO> getAllFeedbacks() {
//         return feedbackService.getAllFeedbacks();
//     }
// }
