package com.luanvan.ThesisTrack_Backend.teachingschedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeachingScheduleRepository extends JpaRepository<TeachingSchedule, Integer> {

  Optional<TeachingSchedule> findById(Integer id);

  List<TeachingSchedule> findByGroupStudentId(Integer groupStudentId);

  List<TeachingSchedule> findByCalenderId(Integer calenderId);

  List<TeachingSchedule> findBySemesterId(Integer semesterId);

  boolean existsByGroupStudent(GroupStudent groupStudent);

}