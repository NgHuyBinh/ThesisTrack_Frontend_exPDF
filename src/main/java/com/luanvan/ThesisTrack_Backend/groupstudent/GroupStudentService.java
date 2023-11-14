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
            TeacherRepository teacherRepository) {
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

    private GroupStudentResponseDTO convertToDTO(GroupStudent groupStudent) {
        GroupStudentResponseDTO dto = new GroupStudentResponseDTO();
        dto.setId(groupStudent.getId());
        dto.setCode(groupStudent.getCode());
        dto.setName(groupStudent.getName());
        dto.setTeacherId(groupStudent.getTeacher().getId());
        return dto;
    }

    // hàm thêm nhóm sinh viên để chia  nhóm sinh viên 
    public void addGroupStudent(GroupStudent groupStudent) {
        Teacher teacher = teacherRepository.findById(groupStudent.getTeacher().getId()).orElseThrow(() -> new NotFoundException("Không tồn tại giảng viên với id " + groupStudent.getTeacher().getId()));

        groupStudentRepository.save(groupStudent);
    }

}
