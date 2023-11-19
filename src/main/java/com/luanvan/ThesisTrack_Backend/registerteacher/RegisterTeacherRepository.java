package com.luanvan.ThesisTrack_Backend.registerteacher;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

@Repository
public interface RegisterTeacherRepository extends JpaRepository<RegisterTeacher, Integer> {
    
    List<RegisterTeacher> findByTeacherId(Integer teacherId);
    List<RegisterTeacher> findByStudentId(Integer studentId);
    boolean existsByStudentAndTeacherAndStatus(Student student, Teacher teacher, Integer status);

    boolean existsByStudentIdAndTeacherIdAndSemesterId(Integer studentId, Integer teacherId, Integer semesterId);

    List<RegisterTeacher> findAll();

    // Tìm thông tin đăng ký theo điều kiện status = 1 và trả về lớp DTO RegisterTeacherInfo
    @Query("SELECT NEW com.luanvan.ThesisTrack_Backend.registerteacher.RegisterTeacherInfo(rt.teacher.id, rt.teacher.name, rt.student.numberStudent, rt.student.name, rt.mark) FROM RegisterTeacher rt WHERE rt.status = 1")
    List<RegisterTeacherInfo> findRegisterTeacherInfoByStatus();

    List<RegisterTeacher> findByStudentIdAndSemesterId(Integer studentId, Integer semesterId);

}
