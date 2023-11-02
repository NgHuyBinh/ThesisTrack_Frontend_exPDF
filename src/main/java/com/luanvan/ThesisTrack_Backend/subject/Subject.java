package com.luanvan.ThesisTrack_Backend.subject;

import com.luanvan.ThesisTrack_Backend.faculty.Faculty;
//import com.luanvan.ThesisTrack_Backend.semester.Semester;
// import com.luanvan.ThesisTrack_Backend.period.Period;

import jakarta.persistence.Column;
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
public class Subject { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String code;

    // thêm số tín chỉ cho môn học
    private Integer credit; 

    private String name;

    // // tiết học 
    // @ManyToOne
    // @JoinColumn(name = "period_id")
    // private Period period;

    // Khoa
    // @ManyToOne
    // @JoinColumn(name = "faculty_id")
    // private Faculty faculty;

    // // thêm phòng để biết chỗ học
    // private String room;

}
