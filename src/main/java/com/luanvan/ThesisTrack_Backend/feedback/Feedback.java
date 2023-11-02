package com.luanvan.ThesisTrack_Backend.feedback;

import java.time.LocalDate;

import com.luanvan.ThesisTrack_Backend.student.Student;

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
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "student_id")
    @ManyToOne
    private Student student; 

    private LocalDate sendDate = LocalDate.now();

    private Integer status = 0;

    private String note = "";

}
