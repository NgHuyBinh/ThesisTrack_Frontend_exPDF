package com.luanvan.ThesisTrack_Backend.groupstudent;

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

// chia nhóm cho sinh viên 

public class GroupStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // mã nhóm sinh viên báo cáo
    private String code;
    
    // tên nhóm sinh viên báo cáo vd: Thầy Tuấn nhóm 1, nhóm 2,..
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

}