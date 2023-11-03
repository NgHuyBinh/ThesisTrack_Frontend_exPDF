package com.luanvan.ThesisTrack_Backend.registerteacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;

import com.luanvan.ThesisTrack_Backend.exception.AlreadyExistsException;
import com.luanvan.ThesisTrack_Backend.exception.InvalidValueException;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
// import com.luanvan.ThesisTrack_Backend.registertopic.RegisterTopic;
import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.student.StudentRepository;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import com.luanvan.ThesisTrack_Backend.teacher.TeacherRepository;
// import com.luanvan.ThesisTrack_Backend.topic.Topic;
import com.luanvan.ThesisTrack_Backend.topic.TopicRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
// import java.util.ArrayList;

@Service
public class RegisterTeacherService {

    private RegisterTeacherRepository registerTeacherRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public RegisterTeacherService(RegisterTeacherRepository registerTeacherRepository,
            StudentRepository studentRepository, TeacherRepository teacherRepository, TopicRepository topicRepository) {
        this.registerTeacherRepository = registerTeacherRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    // đăng ký  sinh viên 
    public void createRegisterTeacher(Integer studentId, Integer teacherId, Float mark) {
        // Kiểm tra xem đã tồn tại trong bảng RegisterTeacher chưa
        boolean isExistingRegistration = registerTeacherRepository.existsByStudentIdAndTeacherId(studentId, teacherId);
    
        if (isExistingRegistration) {
            throw new AlreadyExistsException("Bạn đã đăng ký với giảng viên này trước đó.");
        }
    
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Sinh viên không tồn tại."));
    
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new NotFoundException("Giảng viên không tồn tại."));
    
        if (student.getStatus() == 1) {
            throw new InvalidValueException("Sinh viên đã đăng ký với một giảng viên khác trước đó.");
        }
    
        if (mark == null || mark == 0.0f) {
            throw new InvalidValueException("Vui lòng nhập điểm trung bình.");
        }
    
        RegisterTeacher registerTeacher = new RegisterTeacher();
        registerTeacher.setStudent(student);
        registerTeacher.setTeacher(teacher);
        registerTeacher.setMark(mark);
        registerTeacher.setStatus(0);
    
        registerTeacherRepository.save(registerTeacher);

        
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

    
    
    // public ResponseEntity<String> createRegisterTeacher(Integer studentId, Integer teacherId, Float mark) {
    //     // Kiểm tra xem đã tồn tại trong bảng RegisterTeacher chưa
    //     boolean isExistingRegistration = registerTeacherRepository.existsByStudentIdAndTeacherId(studentId, teacherId);

    //     if (isExistingRegistration) {
    //         throw new AlreadyExistsException("Bạn đã đăng ký với giảng viên này trước đó.");
    //     }

    //     Student student = studentRepository.findById(studentId)
    //             .orElseThrow(() -> new NotFoundException("Sinh viên không tồn tại."));

    //     Teacher teacher = teacherRepository.findById(teacherId)
    //             .orElseThrow(() -> new NotFoundException("Giảng viên không tồn tại."));

    //     if (student.getStatus() == 1) {
    //         throw new InvalidValueException("Sinh viên đã đăng ký với một giảng viên khác trước đó.");
    //     }

    //     if (mark == null || mark == 0.0f) {
    //         throw new InvalidValueException("Vui lòng nhập điểm trung bình.");
    //     }
        

    //     RegisterTeacher registerTeacher = new RegisterTeacher();
    //     registerTeacher.setStudent(student);
    //     registerTeacher.setTeacher(teacher);
    //     registerTeacher.setMark(mark);
    //     registerTeacher.setStatus(0);

    //     registerTeacherRepository.save(registerTeacher);

    //     return ResponseEntity.ok("Thêm đăng ký giảng viên thành công.");
    // }

    // private RegisterTeacherResponseDTO mapRegisterTeacherToResponseDTO(RegisterTeacher registerTeacher) {
    //     RegisterTeacherResponseDTO responseDTO = new RegisterTeacherResponseDTO();
    //     responseDTO.setId(registerTeacher.getId());
    //     responseDTO.setStudentId(registerTeacher.getStudent().getId());
    //     responseDTO.setTeacherId(registerTeacher.getTeacher().getId());
    //     responseDTO.setMark(registerTeacher.getMark());
    //     responseDTO.setStatus(registerTeacher.getStatus());
    //     return responseDTO;
    // }

    public List<RegisterTeacherResponseDTO> getAllRegisterTeachers() {
        // Sử dụng registerTeacherRepository để lấy danh sách tất cả đăng ký giảng viên
        List<RegisterTeacher> registerTeachers = registerTeacherRepository.findAll();

        // Chuyển đổi danh sách thành DTO
        List<RegisterTeacherResponseDTO> responseDTOs = new ArrayList<>();
        for (RegisterTeacher registerTeacher : registerTeachers) {
            RegisterTeacherResponseDTO responseDTO = new RegisterTeacherResponseDTO(
                registerTeacher.getId(),
                registerTeacher.getStudent().getId(),
                registerTeacher.getStudent().getName(),
                registerTeacher.getTeacher().getId(),
                registerTeacher.getMark(),
                registerTeacher.getStatus(),
                registerTeacher.getNote()
            );
            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }

    // public List<RegisterTeacherResponseDTO> getRegisterTeachersByTeacher(Integer teacherId) {
    //     List<RegisterTeacher> registerTeachers = registerTeacherRepository.findByTeacherId(teacherId);
    //     return registerTeachers.stream()
    //             .map(this::mapRegisterTeacherToResponseDTO)
    //             .collect(Collectors.toList());
    // }

    public List<RegisterTeacherResponseDTO> getRegisterTeachersByTeacher(Integer teacherId) {
        List<RegisterTeacher> registerTeachers = registerTeacherRepository.findByTeacherId(teacherId);
        
        return registerTeachers.stream()
                .map(registerTeacher -> new RegisterTeacherResponseDTO(
                    registerTeacher.getId(),
                    registerTeacher.getStudent().getId(),
                    registerTeacher.getStudent().getName(),
                    registerTeacher.getTeacher().getId(),
                    registerTeacher.getMark(),
                    registerTeacher.getStatus(),
                    registerTeacher.getNote()
                ))
                .collect(Collectors.toList());
    }
    
    // cập nhật trang thái đăng ký giảng viên của sinh viên.
    public void updateStatus(Integer id, RegisterTeacherResponseDTO registerTeacher) {
        RegisterTeacher r = registerTeacherRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tồn tại đăng ký giảng viên"));
        if (registerTeacher.getNote() != null) {
            r.setNote(registerTeacher.getNote());
        }
        r.setStatus(registerTeacher.getStatus());
        registerTeacherRepository.save(r);
    }

    // lấy thông tin sinh viên đã có giảng viên hướng  dẫn (để lấy danh sách này chia nhóm sinh viên)
    public List<RegisterTeacherInfo> findRegisterTeacherInfoByStatus() {
        return registerTeacherRepository.findRegisterTeacherInfoByStatus();
    }

    
}