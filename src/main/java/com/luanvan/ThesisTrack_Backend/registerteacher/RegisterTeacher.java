package com.luanvan.ThesisTrack_Backend.registerteacher;

import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegisterTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @NotNull(message = "ID sinh viên không được null")
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // @NotNull(message = "Id giảng viên không được null")
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @NotNull(message = "Không thể để trống điểm trung bình tích lũy.")
    // private String mark;
    private Float mark;

    private Integer status = 0;

    private String note  = new String();
}

