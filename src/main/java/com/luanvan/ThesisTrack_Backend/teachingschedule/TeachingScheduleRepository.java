package com.luanvan.ThesisTrack_Backend.teachingschedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;
import com.luanvan.ThesisTrack_Backend.semester.Semester;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeachingScheduleRepository extends JpaRepository<TeachingSchedule, Integer> {

  Optional<TeachingSchedule> findById(Integer id);

  List<TeachingSchedule> findByGroupStudentId(Integer groupStudentId);

  List<TeachingSchedule> findByCalenderId(Integer calenderId);

  List<TeachingSchedule> findBySemesterId(Integer semesterId);

  boolean existsByGroupStudent(GroupStudent groupStudent);

  // khác
  // @Query("SELECT ts FROM TeachingSchedule ts JOIN ts.groupStudent gst WHERE gst.teacher.id =:teacherId")
  // Optional<TeachingSchedule> findTeachingScheduleByTeacherId(@Param("teacherId") Integer teacherId);
  @Query("SELECT ts FROM TeachingSchedule ts JOIN ts.groupStudent gst WHERE gst.teacher.id =:teacherId")
  List<TeachingSchedule> findTeachingScheduleByTeacherId(@Param("teacherId") Integer teacherId);

}