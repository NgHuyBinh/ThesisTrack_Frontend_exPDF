package com.luanvan.ThesisTrack_Backend.markteacher;

import java.time.LocalDate;

import com.luanvan.ThesisTrack_Backend.semester.Semester;
import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

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
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // ngày chấm điểm 
    private LocalDate sendDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    // hình thức quyển báo cáo 
    private Float mark11;
    private String note11 = new String();

    // tóm tắt tiếng anh tiếng việt 
    private Float mark12;
    private String note12 = new String();

    // phần giới thiệu
    private Float mark13;
    private String note13 = new String();

    // mô tả bài toán
    private Float mark14;
    private String note14 = new String();

    // thiết kế và cài đặt giải pháp 
    private Float mark15;
    private String note15 = new String();

    // Kiểm thử và đánh giá
    private Float mark2;
    private String note2 = new String();

    // phần kết luận 
    private Float mark21;
    private String note21 = new String();

    // trình bày báo cáo 
    private Float mark22;
    private String note22 = new String();

    // trả lời hất vấn 
    private Float mark23;
    private String note23 = new String();

    // tổng điểm 
    private Float sumMark;
}
