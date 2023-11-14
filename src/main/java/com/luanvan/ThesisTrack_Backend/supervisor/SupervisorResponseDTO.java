package com.luanvan.ThesisTrack_Backend.supervisor;

import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Integer
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupervisorResponseDTO {
    private Integer id;
    private Teacher teacher1;
    private Teacher teacher2;
    private Teacher teacher3;
    private Integer status;

}
