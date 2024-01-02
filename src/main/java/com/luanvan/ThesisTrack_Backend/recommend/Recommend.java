package com.luanvan.ThesisTrack_Backend.recommend;

import com.luanvan.ThesisTrack_Backend.calenderthesis.CalenderThesis;
// import com.luanvan.ThesisTrack_Backend.student.Student;
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
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "calenderthesis_id")
    private CalenderThesis calenderThesis;
    @ManyToOne
    @JoinColumn(name = "thuky_id")
    private Teacher teacher1;
    private Boolean status1 = false;
    @ManyToOne
    @JoinColumn(name = "chutich_id")
    private Teacher teacher2;
    private Boolean status2 = false;
    @ManyToOne
    @JoinColumn(name = "uyvien_id")
    private Teacher teacher3;
    private Boolean status3 = false;
}
