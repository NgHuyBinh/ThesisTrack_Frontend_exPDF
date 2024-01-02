package com.luanvan.ThesisTrack_Backend.groupstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.student.StudentRepository;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
// import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import com.luanvan.ThesisTrack_Backend.teacher.TeacherRepository;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import java.util.Optional;

@Service
public class GroupStudentService {
    private final GroupStudentRepository groupStudentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    @Autowired
    public GroupStudentService(
            GroupStudentRepository groupStudentRepository,
            TeacherRepository teacherRepository,StudentRepository studentRepository) {
        this.groupStudentRepository = groupStudentRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
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

    // public void addGroupStudent(GroupStudent groupStudent) {
    //     // Teacher teacher = 
    //     teacherRepository.findById(groupStudent.getTeacher().getId()).orElseThrow(
    //             () -> new NotFoundException("Không tồn tại giảng viên với id " + groupStudent.getTeacher().getId()));
    //     groupStudentRepository.save(groupStudent);

    // }
    public GroupStudent addGroupStudent(GroupStudent groupStudent) {
        // You can perform any additional logic/validation here before saving to the database
        return groupStudentRepository.save(groupStudent);
    }
    // public void addGroupStudent(GroupStudentRequestDTO requestDTO) {
    //     GroupStudent groupStudent = new GroupStudent();
    //     for(Integer i: requestDTO.getStudentIds()) {
    //         Student s = studentRepository.findById(i).orElseThrow(() -> new NotFoundException("Không tồn tại sinh viên này"));
    //         groupStudent.addStudent(s);
    //     }
    //     Teacher teacher = teacherRepository.findById(requestDTO.getTeacherId()).orElseThrow(() -> new NotFoundException("Không tồn tại giang viên này"));
    //     groupStudent.setTeacher(teacher);
    //     groupStudent.setCode(requestDTO.getCode());
    //     groupStudent.setNote(requestDTO.getNote());
    //     groupStudentRepository.save(groupStudent);
    // }

    // xóa nhóm sinh viên 
    public void deleteGroup(Integer groupId) {
        GroupStudent groupStudent = groupStudentRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy nhóm sinh viên với ID: " + groupId));
        groupStudentRepository.delete(groupStudent);
    }

    // chỉnh sửa nhóm sinh viên
    public String updateGroups(Integer id, GroupStudent updateGroups){
        Optional<GroupStudent> optionalGroupStudent = groupStudentRepository.findById(id);
        if (optionalGroupStudent.isPresent()) {
            GroupStudent existingGroup = optionalGroupStudent.get();

            if (updateGroups.getNote() != null){
                existingGroup.setNote(updateGroups.getNote());
            }
            if (updateGroups.getCode() != null) {
                existingGroup.setCode(updateGroups.getCode());
            }
            groupStudentRepository.save(existingGroup);

            return "Cập nhật thông tin nhóm sinh viên thành công";
        }
        return "Không tìm thấy nhóm sinh viên có id" + id + ".Cập nhật nhóm sinh viên thất bại!!";
    }

}





