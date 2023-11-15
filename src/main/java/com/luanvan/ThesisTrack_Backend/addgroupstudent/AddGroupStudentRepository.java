package com.luanvan.ThesisTrack_Backend.addgroupstudent;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;
import com.luanvan.ThesisTrack_Backend.student.Student;

@Repository
public interface AddGroupStudentRepository extends JpaRepository<AddGroupStudent, Integer> {
     // Kiểm tra sự tồn tại của một mục chia nhóm sinh viên dựa trên sinh viên và
     // nhóm sinh viên
     boolean existsByStudentAndGroupStudent(Student student, GroupStudent groupStudent);

     // Lấy danh sách các mục chia nhóm sinh viên dựa trên nhóm sinh viên
     List<AddGroupStudent> findByGroupStudent(GroupStudent groupStudent);

     // Lấy một mục chia nhóm sinh viên dựa trên sinh viên
     Optional<AddGroupStudent> findByStudent(Student student);
     
     // com.itextpdf.text.List findByGroupStudentId(Integer groupStudentId);
     List<AddGroupStudent> findAllByGroupStudentId(Integer groupStudentId);
}