package com.luanvan.ThesisTrack_Backend.registerteacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTeacherRequestDTO {
    private Integer status;
    private String note;
}
