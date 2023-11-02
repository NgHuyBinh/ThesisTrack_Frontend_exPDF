package com.luanvan.ThesisTrack_Backend.registerteacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTeacherInfo {
    private Integer teacherId;
    private String teacherName;
    private String studentNumber;
    private String studentName;

    // Getter và Setter
    // public RegisterTeacherInfo(Integer teacherId, String teacherName, String studentNumber, String studentName) {
    //     this.teacherId = teacherId;
    //     this.teacherName = teacherName;
    //     this.studentNumber = studentNumber;
    //     this.studentName = studentName;
    // }
    
}
