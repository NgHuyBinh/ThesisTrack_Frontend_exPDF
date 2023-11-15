package com.luanvan.ThesisTrack_Backend.markteacher;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.luanvan.ThesisTrack_Backend.semester.Semester;
import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MarkResponseDTO {
    private Integer id;

    private Teacher teacher;

    private Student student;

    private Semester semester;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDate day;

    private LocalDate sendDate;
    // private LocalDate sendDate = LocalDate.now();

    // hình thức quyển báo cáo
    private Float mark11;
    private String note11;

    // tóm tắt tiếng anh tiếng việt
    private Float mark12;
    private String note12;

    // phần giới thiệu
    private Float mark13;
    private String note13;

    // mô tả bài toán
    private Float mark14;
    private String note14;

    // thiết kế và cài đặt giải pháp
    private Float mark15;
    private String note15;

    // Kiểm thử và đánh giá
    private Float mark2;
    private String note2;

    // phần kết luận
    private Float mark21;
    private String note21;

    // trình bày báo cáo
    private Float mark22;
    private String note22;

    // trả lời hất vấn
    private Float mark23;
    private String note23;

    // điểm tổng
    private Float sumMark;

}
