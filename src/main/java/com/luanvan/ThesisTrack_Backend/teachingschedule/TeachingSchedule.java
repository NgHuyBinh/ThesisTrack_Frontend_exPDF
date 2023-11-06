package com.luanvan.ThesisTrack_Backend.teachingschedule;


import com.luanvan.ThesisTrack_Backend.calender.Calender;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;
import com.luanvan.ThesisTrack_Backend.semester.Semester;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Entity
// lịch báo cáo luận văn
public class TeachingSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "calender_id")
    private Calender calender;
    // học kỳ
    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "groupStudent_id")
    private GroupStudent groupStudent;
}
    // @ManyToOne
    // @JoinColumn(name = "supervisor_id")
    // private Supervisor supervisor;

    // // phòng
    // private String room;

    // // tuần
    // private Integer week;

    // // thứ mấy trong tuần
    // private String thu;

    // // ngày nào

    // // @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    // private LocalDate day;

    // // buổi sáng hay chiều
    // private String period;

