package com.luanvan.ThesisTrack_Backend.teacher;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherResponseDTO {
    private Integer id;
    private String numberTeacher;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String major;
    private LocalDate birthday;
    private Integer gender;
}
