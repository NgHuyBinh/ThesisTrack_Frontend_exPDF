package com.luanvan.ThesisTrack_Backend.groupstudent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface GroupStudentRepository extends JpaRepository <GroupStudent, Integer> {
    List<GroupStudent> findByTeacherId(Integer teacherId);

  
}
