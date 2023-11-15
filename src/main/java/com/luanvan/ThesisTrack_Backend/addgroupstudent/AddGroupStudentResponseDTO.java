package com.luanvan.ThesisTrack_Backend.addgroupstudent;

import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;
import com.luanvan.ThesisTrack_Backend.student.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddGroupStudentResponseDTO {
    public AddGroupStudentResponseDTO(Integer id2, String numberStudent, String name, String code, String name2) {
    }

    private Integer id;

    private Student student;

    private GroupStudent groupStudent;
    
}
