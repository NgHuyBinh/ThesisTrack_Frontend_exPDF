package com.luanvan.ThesisTrack_Backend.feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    boolean existsByStudentIdAndStatus(Integer studentId, Integer status);

    List<Feedback> findByStudentId(Integer studentId);
}
