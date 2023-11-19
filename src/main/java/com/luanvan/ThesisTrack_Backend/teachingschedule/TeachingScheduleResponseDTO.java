package com.luanvan.ThesisTrack_Backend.teachingschedule;

import java.time.LocalDate;

import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachingScheduleResponseDTO {

    private Integer id;

    private Integer calender;

    private Integer semester;

    private Integer groupStudent;

    private Integer status = 0;

    // hiển thị thông tin của lịch
    private String room;

    private Integer week;

    private String thu;

    private LocalDate day;

    private String period;

    private String note;

    // hiển thị thông tin của bảng học kỳ
    private String schoolYear;

    private Integer semester_number;

    // nhóm sinh viên
    private String code;
    private String name;
    private Integer teacherId;

    // public void setSemester(Integer id2) {
    // }

    // public TeachingScheduleResponseDTO(Integer integer,
    //         String room, Integer week,
    //         String thu, LocalDate day,
    //         String period, String note,
    //         String schoolYear, Integer semester_number,
    //         Integer teacherId,
    //         String code, String name,
    //         String string2, Integer integer2) {
    //     this.room = room;
    //     this.week = week;
    //     this.thu = thu;
    //     this.day = day;
    //     this.period = period;
    //     this.note = note;
    //     this.schoolYear = schoolYear;
    //     this.semester_number = semester_number;
    //     this.teacherId = teacherId;
    //     this.code = code;
    //     this.name = name;

    // }

    public void setTeacher_id(Teacher teacher) {
    }

    public void teacherId(Integer id2) {
    }

    // public void setTeacher_id(Integer id2) {
    // }

}
