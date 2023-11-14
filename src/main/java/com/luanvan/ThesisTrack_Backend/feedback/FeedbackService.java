package com.luanvan.ThesisTrack_Backend.feedback;

import com.luanvan.ThesisTrack_Backend.student.StudentRepository;
import com.luanvan.ThesisTrack_Backend.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, StudentService studentService
    ,  StudentRepository studentRepository

    ) {
        this.feedbackRepository = feedbackRepository;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Integer id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public void createFeedback(Feedback feedback) {
        // Kiểm tra xem nội dung phản hồi có được nhập không
        if (feedback.getNote() == null || feedback.getNote().isEmpty()) {
            throw new IllegalArgumentException("Nội dung phản hồi không được để trống.");
        }

        // Kiểm tra xem sinh viên có tồn tại không
        // if (!studentService.studentExists(feedback.getStudent().getId())) {
        //     throw new IllegalArgumentException("Sinh viên không tồn tại.");
        // }

        // Kiểm tra xem đã có phản hồi chưa cho sinh viên này
        if (feedbackRepository.existsByStudentIdAndStatus(feedback.getStudent().getId(), feedback.getStatus())) {
            throw new IllegalArgumentException("Sinh viên đã gửi phản hồi trước đó.");
        }

        // Có thể thêm logic kiểm tra và xử lý trước khi lưu feedback vào cơ sở dữ liệu
        feedbackRepository.save(feedback);
        // return "Phản hồi đã được tạo thành công.";

    }

    // cập nhật phản hồi , trả lời phản  hồi của sinh viên 
    public Feedback updateFeedback(Integer id, Feedback updatedFeedback) {
        // Kiểm tra xem feedback có tồn tại không
        Feedback existingFeedback = feedbackRepository.findById(id).orElse(null);
        if (existingFeedback != null) {
            // Cập nhật các thông tin cần thiết
            if (updatedFeedback.getNote() != null) {
                existingFeedback.setNote(updatedFeedback.getNote());
            }

            if (updatedFeedback.getReply() != null) {
                existingFeedback.setReply(updatedFeedback.getReply());
            }

            // Có thể cập nhật thêm các thông tin khác tùy ý

            // Lưu lại vào cơ sở dữ liệu
            return feedbackRepository.save(existingFeedback);
        }
        return null; // Hoặc bạn có thể ném ra một exception tùy theo yêu cầu
    }

    // lấy phản hồi theo sinh viên 
    // public Feedback getFeedbackByStudentId(Integer studentId) {
    //     // Kiểm tra xem sinh viên có tồn tại không
    //     if (!studentService.studentExists(studentId)) {
    //         throw new IllegalArgumentException("Sinh viên không tồn tại.");
    //     }

    //     // Lấy danh sách phản hồi theo ID sinh viên
    //     List<Feedback> feedbackList = feedbackRepository.findByStudentId(studentId);

    //     // Nếu danh sách không rỗng, trả về phản hồi đầu tiên
    //     return feedbackList.stream().findFirst()
    //             .orElseThrow(() -> new IllegalArgumentException("Sinh viên này không có phản hồi."));
    // }

    public List<Feedback> getFeedbacksByStudentId(Integer studentId) {
        // Sử dụng phương thức mới thêm vào repository
        return feedbackRepository.findByStudentId(studentId);
    }

    public void deleteFeedback(Integer id) {
        feedbackRepository.deleteById(id);
    }

}
