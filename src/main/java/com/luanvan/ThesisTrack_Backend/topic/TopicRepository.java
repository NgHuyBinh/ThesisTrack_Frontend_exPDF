package com.luanvan.ThesisTrack_Backend.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
     List<Topic> findByTeacher(Teacher teacher);

     Optional<Topic> findByName(String name);
     List<Topic> findByTeacherId(Integer teacher_id);

    // List<Topic> findByStudentId(Integer studentId);
//    List<Topic> findByStudentId(Integer studentId);
}
