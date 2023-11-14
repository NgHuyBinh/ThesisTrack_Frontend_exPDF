package com.luanvan.ThesisTrack_Backend.markteacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {
    boolean existsByStudentIdAndTeacherIdAndSemesterId(Integer studentId, Integer teacherId, Integer semesterId);

    List<Mark> findByStudentIdAndSemesterId(Integer studentId, Integer semesterId);

    List<Mark> findByTeacherId(Integer teacherId);

    List<Mark> findByStudentId(Integer studentId);

    List<Mark> findBySemesterId(Integer semesterId);
    
} 