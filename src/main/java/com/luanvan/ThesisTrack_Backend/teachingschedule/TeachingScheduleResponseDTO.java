 package com.luanvan.ThesisTrack_Backend.teachingschedule;

 import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;
 import com.luanvan.ThesisTrack_Backend.semester.Semester;
 import com.luanvan.ThesisTrack_Backend.supervisor.Supervisor;
 import lombok.AllArgsConstructor;
 import lombok.Data;
 import lombok.NoArgsConstructor;

 import java.time.LocalDate;

 @Data
 @NoArgsConstructor
 @AllArgsConstructor
 public class TeachingScheduleResponseDTO {
  private Integer id;
  private String room;
  private Integer week;
  private String thu;
  private LocalDate day;
  private String period;
  private Semester semester;
  private GroupStudent groupStudent;
//  private Supervisor supervisor;
 }

