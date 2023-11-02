// package com.luanvan.ThesisTrack_Backend.feedback;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.luanvan.ThesisTrack_Backend.semester.Semester;
// import com.luanvan.ThesisTrack_Backend.semester.SemesterRepository;
// import com.luanvan.ThesisTrack_Backend.student.Student;
// import com.luanvan.ThesisTrack_Backend.student.StudentRepository;

// @Service
// public class FeedbackService {

//     private FeedbackRepository feedbackRepository;
//     private StudentRepository studentRepository;
//     private SemesterRepository semesterRepository;

//     @Autowired
//     public FeedbackService(FeedbackRepository roomFeedbackRepository, StudentRepository studentRepository,
//             SemesterRepository semesterRepository) {
//         this.feedbackRepository = feedbackRepository;
//         this.studentRepository = studentRepository;
//         this.semesterRepository = semesterRepository;
//     }

//     public List<FeedbackResponseDTO> getFeedbackByStudent(Integer id) {
//         List<FeedbackResponseDTO> responseDTOs = new ArrayList<>();
//         if (feedbackRepository.findByStudentId(id).isPresent()) {
//             List<Feedback> roomFeedbacks = feedbackRepository.findByStudentId(id).get();
//             for (Feedback r : roomFeedbacks) {
//                 FeedbackResponseDTO feedbackResponseDTO = new FeedbackResponseDTO();
//                 feedbackResponseDTO.setId(r.getId());
//                 feedbackResponseDTO.setStatus(r.getStatus());
//                 feedbackResponseDTO.setSendDate(r.getSendDate());
//                 responseDTOs.add(feedbackResponseDTO);
//             }
//         }
//         return responseDTOs;
//     }

//     public void addFeedBackFromStudent(Feedback feedback) {

//     }
// }
