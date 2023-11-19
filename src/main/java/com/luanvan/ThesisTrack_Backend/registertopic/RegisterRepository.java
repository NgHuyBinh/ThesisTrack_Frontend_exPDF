package com.luanvan.ThesisTrack_Backend.registertopic;

import java.util.List;
import java.util.Optional;

// import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterTopic, Integer> {
    Optional<RegisterTopic> findByStudentIdAndSemesterId(Integer studentId, Integer semesterId);

    Optional<RegisterTopic> findByTopicIdAndSemesterId(Integer topicId, Integer semesterId);

//    List<RegisterTopic> findAll();
    // Optional<RegisterTopic> findByStudentIdAndSemesterId(Integer studentId, Integer semesterId);
    // List<RegisterTopic> findByTeacherId(Integer id);
    Optional<RegisterTopic> findByStudentId(Integer studentId);
}
