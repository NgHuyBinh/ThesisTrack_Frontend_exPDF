package com.luanvan.ThesisTrack_Backend.groupstudent;

import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

// chia nhóm cho sinh viên
public class GroupStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // mã nhóm sinh viên báo cáo
    @Column(unique = true)
    private String code;

    // // tên nhóm sinh viên báo cáo vd: Thầy Tuấn nhóm 1, nhóm 2,..
    // private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private String note = new String();

}