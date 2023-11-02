 package com.luanvan.ThesisTrack_Backend.teachingschedule;

 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

 import java.time.LocalDate;
 import java.util.List;

 @Repository
 public interface TeachingScheduleRepository extends JpaRepository<TeachingSchedule, Integer> {
  boolean existsByGroupStudentIdAndRoomAndWeekAndThuAndDayAndPeriod(
          Integer groupStudentId, String room, Integer week, String thu, LocalDate day, String period);

  List<TeachingSchedule> findByGroupStudentId(Integer groupStudentId);

  List<TeachingSchedule> findByGroupStudentIdAndRoomAndThuAndDay(Integer id, String room, String thu, LocalDate day);
 }