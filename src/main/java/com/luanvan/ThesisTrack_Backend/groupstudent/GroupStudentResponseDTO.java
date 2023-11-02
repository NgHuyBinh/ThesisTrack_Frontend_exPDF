package com.luanvan.ThesisTrack_Backend.groupstudent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupStudentResponseDTO {
    private Integer id;
    private String code;
    private String name;
}
