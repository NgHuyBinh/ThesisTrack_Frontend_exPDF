package com.luanvan.ThesisTrack_Backend.calenderthesis;

import com.luanvan.ThesisTrack_Backend.calender.Calender;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;

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
public class CalenderThesis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "calender_id")
    private Calender calender;
    @ManyToOne
    @JoinColumn(name = "groupS_id")
    private GroupStudent groupStudent;

}
