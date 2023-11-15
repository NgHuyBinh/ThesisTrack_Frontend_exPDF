package com.luanvan.ThesisTrack_Backend.addgroupstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.DuplicateException;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudentRepository;
import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.student.StudentRepository;

import java.util.List;

@Service
public class AddGroupStudentService {
    private final AddGroupStudentRepository addGroupStudentRepository;
    private final StudentRepository studentRepository;
    private final GroupStudentRepository groupStudentRepository;

    @Autowired
    public AddGroupStudentService(AddGroupStudentRepository addGroupStudentRepository,
            StudentRepository studentRepository, GroupStudentRepository groupStudentRepository) {
        this.addGroupStudentRepository = addGroupStudentRepository;
        this.studentRepository = studentRepository;
        this.groupStudentRepository = groupStudentRepository;
    }

    // chia nhóm sinh viên
    public void createAddGroupStudent(AddGroupStudent addGroupStudent) {
        Student student = studentRepository.findById(addGroupStudent.getStudent().getId()).orElseThrow(
                () -> new NotFoundException("Không tồn tại sinh viên với id " + addGroupStudent.getStudent().getId()));

        GroupStudent groupStudent = groupStudentRepository.findById(addGroupStudent.getGroupStudent().getId())
                .orElseThrow(() -> new NotFoundException(
                        "Không tồn tại nhóm sinh viên với id" + addGroupStudent.getGroupStudent().getId()));

        if (addGroupStudentRepository.existsByStudentAndGroupStudent(student, groupStudent)) {
            throw new DuplicateException("Sinh viên này đã được chia nhóm trước đó rồi.");
        }

        addGroupStudentRepository.save(addGroupStudent);
    }

    // lấy tất cả
    public List<AddGroupStudent> getAllAddGroupStudents() {
        return addGroupStudentRepository.findAll();
    }

    // lấy thông tin theo nhóm sinh viên
    public List<AddGroupStudent> getAddGroupStudentsByGroupStudent(Integer groupStudentId) {
        return addGroupStudentRepository.findAllByGroupStudentId(groupStudentId);
    }

    // lấy thông tin theo id sinh viên
    public AddGroupStudent getAddGroupStudentByStudent(Integer studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Không tồn tại sinh viên với id " + studentId));
        return addGroupStudentRepository.findByStudent(student)
                .orElseThrow(() -> new NotFoundException("Sinh viên không thuộc nhóm nào."));
    }
}
