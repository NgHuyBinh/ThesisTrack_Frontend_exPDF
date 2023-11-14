package com.luanvan.ThesisTrack_Backend.feedback;

import java.time.LocalDate;

import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDate sendDate = LocalDate.now();

    private Integer status = 0;

    @NotNull(message = "Phản hồi không thể để trống nội dung.")
    private String note;

    private String reply = new String();

    public void setStudent(Student student) {
        this.student = student;
    }

}
