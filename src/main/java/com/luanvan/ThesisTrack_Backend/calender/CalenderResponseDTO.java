package com.luanvan.ThesisTrack_Backend.calender;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CalenderResponseDTO {
    private Integer id;
   
    private String Room;

    private Integer week;

    private String thu;

    private LocalDate day;

    private String period;

    private String note;
    
}
    