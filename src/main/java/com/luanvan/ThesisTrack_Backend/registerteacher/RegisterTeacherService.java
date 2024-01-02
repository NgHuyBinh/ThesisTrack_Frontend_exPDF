package com.luanvan.ThesisTrack_Backend.registerteacher;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.luanvan.ThesisTrack_Backend.exception.AlreadyExistsException;
import com.luanvan.ThesisTrack_Backend.exception.InvalidValueException;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
import com.luanvan.ThesisTrack_Backend.semester.Semester;
import com.luanvan.ThesisTrack_Backend.semester.SemesterRepository;
// import com.luanvan.ThesisTrack_Backend.registertopic.RegisterTopic;
import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.student.StudentRepository;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import com.luanvan.ThesisTrack_Backend.teacher.TeacherRepository;
// import com.luanvan.ThesisTrack_Backend.topic.Topic;
import com.luanvan.ThesisTrack_Backend.topic.TopicRepository;

// import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
// import java.util.ArrayList;

@Service
public class RegisterTeacherService {

    private RegisterTeacherRepository registerTeacherRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private SemesterRepository semesterRepository;

    @Autowired
    public RegisterTeacherService(SemesterRepository semesterRepository,
            RegisterTeacherRepository registerTeacherRepository,
            StudentRepository studentRepository, TeacherRepository teacherRepository, TopicRepository topicRepository) {
        this.registerTeacherRepository = registerTeacherRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.semesterRepository = semesterRepository;
    }

    // cập nhật status sau đăng ký và phê duyệt của giảng viên
    public void updateStatus(Integer id, RegisterTeacher registerTeacher) {
        RegisterTeacher r = registerTeacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tồn tại đăng ký giảng viên"));
        if (registerTeacher.getMark() != null) {
            r.setMark(registerTeacher.getMark());
        }
        r.setStatus(registerTeacher.getStatus());
        registerTeacherRepository.save(r);
    }
    public void updateStatus2(Integer id, RegisterTeacherRequestDTO registerTeacher) {
        RegisterTeacher r = registerTeacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tồn tại đăng ký giảng viên"));
        r.setNote(registerTeacher.getNote());
        r.setStatus(registerTeacher.getStatus());
        registerTeacherRepository.save(r);
    }
    public List<RegisterTeacherResponseDTO> getAllRegisterTeachers() {
        // Sử dụng registerTeacherRepository để lấy danh sách tất cả đăng ký giảng viên
        List<RegisterTeacher> registerTeachers = registerTeacherRepository.findAll();

        // Chuyển đổi danh sách thành DTO
        List<RegisterTeacherResponseDTO> responseDTOs = new ArrayList<>();
        for (RegisterTeacher registerTeacher : registerTeachers) {
            RegisterTeacherResponseDTO responseDTO = new RegisterTeacherResponseDTO(
                    registerTeacher.getId(),
                    registerTeacher.getStudent().getId(),
                    registerTeacher.getStudent().getNumberStudent(),
                    registerTeacher.getStudent().getName(),
                    registerTeacher.getTeacher().getId(),
                    registerTeacher.getMark(),
                    registerTeacher.getStatus(),
                    registerTeacher.getNote());
            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }

    public List<RegisterTeacherResponseDTO> getRegisterTeachersByTeacher(Integer teacherId) {
        List<RegisterTeacher> registerTeachers = registerTeacherRepository.findByTeacherId(teacherId);

        return registerTeachers.stream()
                .map(registerTeacher -> new RegisterTeacherResponseDTO(
                        registerTeacher.getId(),
                        registerTeacher.getStudent().getId(),
                        registerTeacher.getStudent().getNumberStudent(),
                        registerTeacher.getStudent().getName(),
                        registerTeacher.getTeacher().getId(),
                        registerTeacher.getMark(),
                        registerTeacher.getStatus(),
                        registerTeacher.getNote()))
                .collect(Collectors.toList());
    }

    // cập nhật trang thái đăng ký giảng viên của sinh viên.
    public void updateStatus(Integer id, RegisterTeacherResponseDTO registerTeacher) {
        RegisterTeacher r = registerTeacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tồn tại đăng ký giảng viên"));
        if (registerTeacher.getNote() != null) {
            r.setNote(registerTeacher.getNote());
        }
        r.setStatus(registerTeacher.getStatus());
        registerTeacherRepository.save(r);
    }

    // lấy thông tin sinh viên đã có giảng viên hướng dẫn (để lấy danh sách này chia
    // nhóm sinh viên)
    public List<RegisterTeacherInfo> findRegisterTeacherInfoByStatus() {
        return registerTeacherRepository.findRegisterTeacherInfoByStatus();
    }

    // đăng ký giảng viên của sinh viên 
    public void createRegisterTeacher(RegisterTeacher registerTeacher) {
        Student student = studentRepository.findById(registerTeacher.getStudent().getId())
                .orElseThrow(() -> new NotFoundException(
                        "Không tồn tại sinh viên vói id " + registerTeacher.getStudent().getId()));
        Teacher teacher = teacherRepository.findById(registerTeacher.getTeacher().getId())
                .orElseThrow(() -> new NotFoundException(
                        "Không tồn tại giảng viên vói id " + registerTeacher.getTeacher().getId()));
        Semester semester = semesterRepository.findById(registerTeacher.getSemester().getId())
                .orElseThrow(() -> new NotFoundException(
                        "Không tồn tại học kỳ vói id " + registerTeacher.getSemester().getId()));
        if (registerTeacher.getMark() <= 0 || registerTeacher.getMark() > 4) {
            throw new InvalidValueException("Vui lòng nhập điểm lớn hơn 0 và bé hơn hoặc bằng 4");
        }
        if(LocalDate.now().isBefore(semester.getRtStartDate()) || LocalDate.now().isAfter(semester.getRtEndDay())) {
            throw new InvalidValueException("Chưa đến thời gian đăng ký giảng viên.");
        }
        // kiểm tra sinh viên có giảng viên hướng dẫn hay chưa 
        List<RegisterTeacher> registerTeachers = registerTeacherRepository.findByStudentIdAndSemesterId(student.getId(),
                semester.getId());
        for (RegisterTeacher r : registerTeachers) {
            if (r.getStatus() == 1) {
                throw new AlreadyExistsException("Bạn đã thuộc một giảng viên khác rồi");
            }
        }

        boolean isExistingRegistration = registerTeacherRepository.existsByStudentIdAndTeacherIdAndSemesterId(
                registerTeacher.getStudent().getId(), registerTeacher.getTeacher().getId(),
                registerTeacher.getSemester().getId());

        if (isExistingRegistration) {
            throw new AlreadyExistsException("Bạn đã đăng ký với giảng viên này trước đó.");
        }

        registerTeacherRepository.save(registerTeacher);
    }
    public void updateStatusRegister(Integer id, ResponseStauts response) {
        RegisterTeacher registerTeacher  = registerTeacherRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Không tồn tại bản đăng ký này"));
        registerTeacher.setNote(response.getNote());
        registerTeacher.setStatus(response.getStatus());
        registerTeacherRepository.save(registerTeacher);
        List<RegisterTeacher> registerTeachers = registerTeacherRepository.findAll();
        for(RegisterTeacher r : registerTeachers) {
            if(response.getStatus() == 1) {
                if(r.getStatus() !=1 &&registerTeacher.getStudent().getId() == r.getStudent().getId()) {
                    registerTeacherRepository.deleteById(r.getId());
                }
            }
            
        }
    }
    public List<RegisterTeacher> getRegisterByStudentId(Integer id) {
        return registerTeacherRepository.findByStudentId(id);
    }

}