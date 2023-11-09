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
// import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// import lombok.ToString;

@Data

@NoArgsConstructor
@AllArgsConstructor
@Entity
// @ToString
// @Builder
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

    private Integer status = 0 ;


}