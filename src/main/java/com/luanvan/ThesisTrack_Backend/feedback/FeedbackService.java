package com.luanvan.ThesisTrack_Backend.feedback;

import com.luanvan.ThesisTrack_Backend.exception.AlreadyExistsException;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.student.StudentRepository;
import com.luanvan.ThesisTrack_Backend.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, StudentService studentService,
            StudentRepository studentRepository

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

    // phản hồi sinh viên
    public void createFeedback(Feedback feedback) {
        Student student = studentRepository.findById(feedback.getStudent().getId()).orElseThrow(
                () -> new NotFoundException("Không tồn tại sinh viên có id " + feedback.getId()));
        // Kiểm tra xem nội dung phản hồi có được nhập không
        if (feedback.getNote() == null || feedback.getNote().isEmpty()) {
            throw new IllegalArgumentException("Nội dung phản hồi không được để trống.");
        }

        // Kiểm tra xem đã có phản hồi chưa cho sinh viên này
        // if
        // (feedbackRepository.existsByStudentIdAndStatus(feedback.getStudent().getId(),
        // feedback.getStatus())) {
        // throw new AlreadyExistsException("Sinh viên đã gửi phản hồi trước đó.");
        // }

        // Có thể thêm logic kiểm tra và xử lý trước khi lưu feedback vào cơ sở dữ liệu
        feedbackRepository.save(feedback);
        // return "Phản hồi đã được tạo thành công.";

    }

    // cập nhật phản hồi , trả lời phản hồi của sinh viên
    public String updateFeedback(Integer id, Feedback updatedFeedback) {
        // Kiểm tra xem feedback có tồn tại không
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);

        if (optionalFeedback.isPresent()) {
            Feedback existingFeedback = optionalFeedback.get();

            // Cập nhật các thông tin cần thiết
            if (updatedFeedback.getNote() != null) {
                existingFeedback.setNote(updatedFeedback.getNote());
            }

            if (updatedFeedback.getReply() != null) {
                existingFeedback.setReply(updatedFeedback.getReply());
            }

            // Có thể cập nhật thêm các thông tin khác tùy ý
            existingFeedback.setStatus(1);

            // Lưu lại vào cơ sở dữ liệu
            feedbackRepository.save(existingFeedback);

            return "Cập nhật phản hồi thành công!";
        }

        return "Không tìm thấy phản hồi với ID " + id + ". Cập nhật thất bại!";
    }
    // public Feedback updateFeedback(Integer id, Feedback updatedFeedback) {
    // // Kiểm tra xem feedback có tồn tại không
    // Feedback existingFeedback = feedbackRepository.findById(id).orElse(null);
    // if (existingFeedback != null) {
    // // Cập nhật các thông tin cần thiết
    // if (updatedFeedback.getNote() != null) {
    // existingFeedback.setNote(updatedFeedback.getNote());
    // }

    // if (updatedFeedback.getReply() != null) {
    // existingFeedback.setReply(updatedFeedback.getReply());
    // }

    // // Có thể cập nhật thêm các thông tin khác tùy ý
    // existingFeedback.setStatus(1);

    // // Lưu lại vào cơ sở dữ liệu
    // return feedbackRepository.save(existingFeedback);
    // }
    // return null; // Hoặc bạn có thể ném ra một exception tùy theo yêu cầu
    // }

    public List<Feedback> getFeedbacksByStudentId(Integer studentId) {
        // Sử dụng phương thức mới thêm vào repository
        return feedbackRepository.findByStudentId(studentId);
    }

    public void deleteFeedback(Integer id) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        feedbackRepository.delete(feedback);
    }

}
