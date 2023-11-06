package com.luanvan.ThesisTrack_Backend.registerteacher;

// import java.util.List;

// import com.luanvan.ThesisTrack_Backend.student.Student;
// import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

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
    private String numberStudent;
    private String studentName;
    private Integer teacherId;
    // private Student student;
    // private Teacher teacher;
    private Float mark;
    private Integer status;
    private String note;

    // Các setter và getter cho studentId và teacherId
    // public RegisterTeacherResponseDTO(Student student, Teacher teacher, Float
    // mark, Integer status, String note) {
    public RegisterTeacherResponseDTO(Integer studentId, String numberStudent, String studentName, Integer teacherId, Float mark,
            Integer status, String note) {
        this.studentId = studentId;
        this.numberStudent = numberStudent;
        this.studentName = studentName;
        this.teacherId = teacherId;
        this.mark = mark;
        this.status = status;
        this.note = note;
    }

}
