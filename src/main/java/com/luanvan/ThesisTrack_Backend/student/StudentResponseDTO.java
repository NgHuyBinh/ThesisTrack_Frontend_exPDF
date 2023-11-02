package com.luanvan.ThesisTrack_Backend.student;

import java.time.LocalDate;

import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    
    private Integer id;

    private String numberStudent;

    private String name;
    
    private String address;

    private String email;

    private String phone;

    private String major;

    private LocalDate birthday;

    private Integer gender;

    private String classroom;

    private Teacher teacher;

    private Integer status;
}
