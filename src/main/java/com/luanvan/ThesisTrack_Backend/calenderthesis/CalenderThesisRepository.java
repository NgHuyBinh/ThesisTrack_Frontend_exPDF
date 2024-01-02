package com.luanvan.ThesisTrack_Backend.calenderthesis;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CalenderThesisRepository extends JpaRepository<CalenderThesis, Integer>{
    
     Optional<CalenderThesis> findByGroupStudentId(Integer id);
     // Tìm tất cả CalenderThesis dựa trên teacherId
    List<CalenderThesis> findByGroupStudent_Teacher_Id(Integer teacherId);
}
