package com.luanvan.ThesisTrack_Backend.calender;

import java.time.LocalDate;

import org.aspectj.internal.lang.annotation.ajcPrivileged;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ajcPrivileged
@Entity

public class Calender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String room;

    private Integer week;

    private String thu;

    private LocalDate day;

    private String period;

    private String note = new String();
}
