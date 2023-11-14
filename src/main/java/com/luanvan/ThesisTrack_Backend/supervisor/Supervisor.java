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
    @JoinColumn(name = "thuky")
    private Teacher teacher1;

    @ManyToOne
    @JoinColumn(name = "chutich")
    private Teacher teacher2;

    @ManyToOne
    @JoinColumn(name = "uyvien")
    private Teacher teach3;  

    private Integer status = 0;
}
