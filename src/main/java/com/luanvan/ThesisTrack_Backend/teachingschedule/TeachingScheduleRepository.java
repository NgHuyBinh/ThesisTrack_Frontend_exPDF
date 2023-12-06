package com.luanvan.ThesisTrack_Backend.teachingschedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface TeachingScheduleRepository extends JpaRepository<TeachingSchedule, Integer> {

  List<TeachingSchedule> findByCalender_Id(Integer calenderId);

  List<TeachingSchedule> findByAddGroupStudent_Id(Integer addGroupStudentId);

}