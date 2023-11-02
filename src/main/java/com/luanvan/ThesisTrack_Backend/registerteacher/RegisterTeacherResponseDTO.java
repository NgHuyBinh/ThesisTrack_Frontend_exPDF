package com.luanvan.ThesisTrack_Backend.registerteacher;

import java.util.List;

// import com.luanvan.ThesisTrack_Backend.student.Student;
// import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTeacherResponseDTO {
    private Integer id;
    private Integer studentId;
    private Integer teacherId;
    private Float mark;
    private Integer status;
    private String note;

    // Các setter và getter cho studentId và teacherId
    public RegisterTeacherResponseDTO(Integer studentId, Integer teacherId, Float mark, Integer status, String note) {
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.mark = mark;
        this.status = status;
        this.note = note;
    }

}
