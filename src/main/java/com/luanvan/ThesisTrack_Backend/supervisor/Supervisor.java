package com.luanvan.ThesisTrack_Backend.supervisor;

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

// hội đồng báo cáo luận văn tốt nghiệp

public class Supervisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "teacher_id1")
    private Teacher thuKy;

    @ManyToOne
    @JoinColumn(name = "teacher_id2")
    private Teacher chuTich;

    @ManyToOne
    @JoinColumn(name = "teacher_id3")
    private Teacher uyVien;  

    private Integer status;
}
