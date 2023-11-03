package com.luanvan.ThesisTrack_Backend.student;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luanvan.ThesisTrack_Backend.faculty.Faculty;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import com.luanvan.ThesisTrack_Backend.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Tạo bảng trong CSDL
public class Student {
    
    @Id // Đánh dấu đây là ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // trường tăng tự động
    private Integer id;
    
    @Column(unique = true)
    @NotEmpty(message = "Username cannot be empty")
    private String numberStudent;

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    
    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email invalid")
    private String email;

    @NotEmpty(message = "Phone cannot be empty")
    private String phone;

    //chuyên ngành
    @NotEmpty(message = "Major cannot be empty")
    private String major;

    // @NotEmpty(message = "Birthday cannot be empty")
    private LocalDate birthday;

    //giới tính
    @NotNull(message = "Gender cannot be null")
    private Integer gender;

    //lớp
    @NotEmpty(message = "Classroom cannot be empty")
    private String classroom;
    
    // niên khóa
    @NotEmpty(message = "Không để trống niên khóa")
    private String schoolYear;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    // 1 HV thuoc 1 khoa 1 khoa co nhieu SV 
    private Faculty faculty;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    // thêm vào cho chức năng đăng ký giảng viên hướng dẫn
    private int status;

    // public Student orElse(Object object) {
    //     return null;
    // }

    // thêm vào cho chức năng chia nhóm sinh viên
//    @ManyToOne(optional = true)
//    @JoinColumn(name="groupstudent_id")
//    private GroupStudent groupStudent;
//
}
