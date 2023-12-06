package com.luanvan.ThesisTrack_Backend.groupstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
// import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import com.luanvan.ThesisTrack_Backend.teacher.TeacherRepository;

import java.util.List;
import java.util.Optional;

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

    public List<GroupStudent> getAllGroupStudents() {
        return groupStudentRepository.findAll();
    }

    public Optional<GroupStudent> getGroupStudentById(Integer id) {
        return groupStudentRepository.findById(id);
    }

    public List<GroupStudent> getGroupStudentsByTeacherId(Integer teacherId) {
        return groupStudentRepository.findByTeacherId(teacherId);
    }

    public void addGroupStudent(GroupStudent groupStudent) {
        // Teacher teacher = 
        teacherRepository.findById(groupStudent.getTeacher().getId()).orElseThrow(
                () -> new NotFoundException("Không tồn tại giảng viên với id " + groupStudent.getTeacher().getId()));
        groupStudentRepository.save(groupStudent);

    }

    // xóa nhóm sinh viên 
    public void deleteGroup(Integer groupId) {
        GroupStudent groupStudent = groupStudentRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhóm sinh viên với ID: " + groupId));
        groupStudentRepository.delete(groupStudent);
    }

}
