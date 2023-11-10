package com.luanvan.ThesisTrack_Backend.groupstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import com.luanvan.ThesisTrack_Backend.teacher.TeacherRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupStudentService {
    private final GroupStudentRepository groupStudentRepository;
    private final TeacherRepository teacherRepository;

    @Autowired
    public GroupStudentService(
        GroupStudentRepository groupStudentRepository,
        TeacherRepository teacherRepository
        ) {
        this.groupStudentRepository = groupStudentRepository;
        this.teacherRepository = teacherRepository;
    }
        public List<GroupStudentResponseDTO> getAllGroupStudents() {
        List<GroupStudent> groupStudents = groupStudentRepository.findAll();
        return groupStudents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<GroupStudentResponseDTO> getGroupStudentById(Integer id) {
        Optional<GroupStudent> groupStudent = groupStudentRepository.findById(id);
        return groupStudent.map(this::convertToDTO);
    }

    public List<GroupStudentResponseDTO> getGroupStudentsByTeacherId(Integer teacherId) {
        List<GroupStudent> groupStudents = groupStudentRepository.findByTeacherId(teacherId);
        return groupStudents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

        private boolean teacherExists(Integer teacherId) {
        return teacherRepository.existsById(teacherId);
    }

    private GroupStudentResponseDTO convertToDTO(GroupStudent groupStudent) {
        GroupStudentResponseDTO dto = new GroupStudentResponseDTO();
        dto.setId(groupStudent.getId());
        dto.setCode(groupStudent.getCode());
        dto.setName(groupStudent.getName());
        dto.setTeacherId(groupStudent.getTeacher().getId());
        return dto;
    }

    private GroupStudent convertToEntity(GroupStudentResponseDTO dto) {
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setCode(dto.getCode());
        groupStudent.setName(dto.getName());

        Optional<Teacher> teacher = teacherRepository.findById(dto.getTeacherId());
        teacher.ifPresent(groupStudent::setTeacher);

        return groupStudent;
    }

   
    // public List<GroupStudent> getAllGroups() {
    //     return groupStudentRepository.findAll();
    // }

    // public GroupStudent getById(Integer id) {
    //     return groupStudentRepository.findById(id).orElse(null);
    // }

    public GroupStudentResponseDTO addGroupStudent(GroupStudentResponseDTO groupStudentDTO) {
        Integer teacherId = groupStudentDTO.getTeacherId();
        
        // Kiểm tra xem giáo viên có tồn tại không
        if (!teacherRepository.existsById(teacherId)) {
            throw new NotFoundException("Không tồn tại giáo viên với ID " + teacherId);
        }
    
        GroupStudent groupStudent = convertToEntity(groupStudentDTO);
        GroupStudent savedGroupStudent = groupStudentRepository.save(groupStudent);
    
        return convertToDTO(savedGroupStudent);
    }
    
    
}
