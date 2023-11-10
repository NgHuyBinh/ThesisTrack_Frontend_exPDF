package com.luanvan.ThesisTrack_Backend.groupstudent;

import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

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
    private Integer teacherId;
    public Teacher getTeacher() {
        return null;
    }

}
