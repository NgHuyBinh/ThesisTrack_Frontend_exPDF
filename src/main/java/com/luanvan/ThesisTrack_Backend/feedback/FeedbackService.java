// package com.luanvan.ThesisTrack_Backend.feedback;

// import org.springframework.stereotype.Service;

// import com.luanvan.ThesisTrack_Backend.student.Student;
// import com.luanvan.ThesisTrack_Backend.student.StudentRepository;
// import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
// import com.luanvan.ThesisTrack_Backend.teacher.TeacherRepository;

// import jakarta.persistence.EntityNotFoundException;

// import java.util.List;

// @Service
// public class FeedbackService {


//     private final FeedbackRepository feedbackRepository;
//     private final StudentRepository studentRepository;
//     private final TeacherRepository teacherRepository;

//     public FeedbackService(FeedbackRepository feedbackRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
//         this.feedbackRepository = feedbackRepository;
//         this.studentRepository = studentRepository;
//         this.teacherRepository = teacherRepository;
//     }

//     public void addFeedback(FeedbackResponseDTO feedbackResponseDTO) {
//         // Kiểm tra sự tồn tại của sinh viên hoặc giảng viên
//         Integer studentId = feedbackResponseDTO.getStudentId();
//         Integer teacherId = feedbackResponseDTO.getTeacherId();
        
//         Student student = studentRepository.findById(studentId).orElse(null);
//         Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
    
//         if (student != null || teacher != null) {
//             // Thêm phản hồi nếu có tồn tại sinh viên hoặc giảng viên
//             Feedback feedback = new Feedback();
//             feedback.setStudent(student);
//             feedback.setTeacher(teacher);
//             feedback.setSendDate(feedbackResponseDTO.getSendDate());
//             feedback.setStatus(feedbackResponseDTO.getStatus());
//             feedback.setNote(feedbackResponseDTO.getNote());
//             feedbackRepository.save(feedback);
//         } else {
//             // Xử lý khi không tìm thấy sinh viên hoặc giảng viên
//             throw new EntityNotFoundException("Không tìm thấy sinh viên hoặc giảng viên.");
//         }
//     }
    

//     public List<FeedbackResponseDTO> getAllFeedbacks() {
//         // Thêm logic để truy vấn và chuyển đổi phản hồi thành danh sách FeedbackResponseDTO
//         // Trả về danh sách FeedbackResponseDTO
//         return null;
//     }
// }
