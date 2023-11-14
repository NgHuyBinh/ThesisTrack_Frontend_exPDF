package com.luanvan.ThesisTrack_Backend.topic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luanvan.ThesisTrack_Backend.subject.Subject;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotEmpty(message = "Tên đề tài không được bỏ trống")
    private String name;

    // khóa ngoại với bảng học phần
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    // khóa ngoại với bảng giảng viên
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public int getStatus() {
        return 0;
    }


    // private Integer studentId;
    // Biến kiểm tra xem đề tài đăng ký chưa?
    // private Boolean status;

}
