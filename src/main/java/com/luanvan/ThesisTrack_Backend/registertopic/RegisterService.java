package com.luanvan.ThesisTrack_Backend.registertopic;

import java.time.LocalDate;
import java.util.ArrayList;
// import java.util.Optional;
import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.AlreadyExistsException;
// import com.luanvan.ThesisTrack_Backend.exception.InvalidValueException;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
import com.luanvan.ThesisTrack_Backend.semester.Semester;
import com.luanvan.ThesisTrack_Backend.semester.SemesterRepository;
import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.student.StudentRepository;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import com.luanvan.ThesisTrack_Backend.teacher.TeacherRepository;
import com.luanvan.ThesisTrack_Backend.topic.Topic;
// import com.luanvan.ThesisTrack_Backend.topic.Topic;
import com.luanvan.ThesisTrack_Backend.topic.TopicRepository;

@Service
public class RegisterService {
    private RegisterRepository registerRepository;
    private StudentRepository studentRepository;
    private TopicRepository topicRepository;
    private SemesterRepository semesterRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public RegisterService(RegisterRepository registerRepository, StudentRepository studentRepository,
            TopicRepository topicRepository, SemesterRepository semesterRepository,
            TeacherRepository teacherRepository) {
        this.registerRepository = registerRepository;
        this.studentRepository = studentRepository;
        this.topicRepository = topicRepository;
        this.semesterRepository = semesterRepository;
        this.teacherRepository = teacherRepository;
    }

    public List<RegisterTopic> getAllRegister() {
        return registerRepository.findAll();
    }

    public void updateStatus(Integer id, RegisterTopic registerTopic) {
        RegisterTopic r = registerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tồn tại đăng ký đề tài"));
        if (registerTopic.getReason() == null) {

            r.setReason(registerTopic.getReason());
        }
        r.setStatus(registerTopic.getStatus());
        registerRepository.save(r);
    }

    public List<RegisterTopic> getAllTopics(Integer teacherId) {
        List<RegisterTopic> registerTopics = registerRepository.findAll();
        List<RegisterTopic> filteredTopics = new ArrayList<>();
        teacherRepository.findById(teacherId).orElseThrow(() -> new NotFoundException("Không tồn tại giảng viên này"));
        Semester semester = semesterRepository.findSesmesterByCurrentDateBetweenStartDateAndEndDate(LocalDate.now())
                .orElseThrow(() -> new NotFoundException("Không tồn tại học kỳ này"));
        if (registerTopics.isEmpty() == false) {
            for (RegisterTopic r : registerTopics) {
                Student student = studentRepository.findById(r.getStudent().getId())
                        .orElseThrow(() -> new NotFoundException("Không tồn tại sinh viên"));
                
                    filteredTopics.add(r);
                
            }
        }

        return filteredTopics;
    }

    public void registerTopic(RegisterTopic registerTopic) {
        // System.out.println("abc");
        // Kiểm tra id sinh viên tồn tại không
        Student student = studentRepository.findById(registerTopic.getStudent().getId())
                .orElseThrow(() -> new NotFoundException("Không tồn tại sinh viên này"));
        Semester semester = semesterRepository.findSesmesterByCurrentDateBetweenStartDateAndEndDate(LocalDate.now())
                .orElseThrow(() -> new NotFoundException("Không có học kỳ phù hợp với ngày hiện tại"));
        registerTopic.setSemester(semester);
        if (registerRepository.findByStudentIdAndSemesterId(student.getId(), semester.getId()).isPresent()) {
            throw new AlreadyExistsException("Sinh viên đã đăng ký đề tài trong học kỳ này rồi");
        }
        if (registerTopic.getTopic() != null) {
            registerTopic.setStatus(1);
            // Kiểm tra đề tài này đã tồn tại trong CSDL chưa
            if (registerRepository.findByTopicIdAndSemesterId(registerTopic.getTopic().getId(), semester.getId())
                    .isPresent()) {
                throw new AlreadyExistsException("Đề tài này đã được đăng ký bởi sinh viên khác rồi");
            }
        }

        // Kiểm tra nếu sinh viên không chọn đề tài giảng viên đưa ra thì phải tự đề
        // xuất đề tài mình
        if (registerTopic.getTopic() == null) {
            if (registerTopic.getTopicName().equals("")) {
                throw new NotFoundException("Không được bỏ trống tên đề tài");
            } else {
                // Kiểm tra đề tài nhập vào có trùng với đề giảng viên đề xuất và đã được đăng
                // ký chưa
                if (registerTopic.getStatus() == 1
                        && topicRepository.findByName(registerTopic.getTopicName()).isPresent()) {
                    throw new AlreadyExistsException("Có sinh viên đã đăng ký đề tài này rồi");
                }
            }

        // registerTopic.getTopic().setId(1);
        }
        // if(registerTopic.getTopic() == null){
        //     registerTopic.getTopic().setId(1);;
        // }
            // registerTopic.getTopic().setId(1);
            
        registerRepository.save(registerTopic);
    }
    public RegisterTopic getByStudentId(Integer id) {
        return registerRepository.findByStudentId(id).
        orElseThrow(() -> new NotFoundException("Không có sinh viên với id: "+id));
    }
}
