package com.luanvan.ThesisTrack_Backend.teacher;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luanvan.ThesisTrack_Backend.faculty.Faculty;
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
public class Teacher {
    
    @Id // Đánh dấu đây là ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // trường tăng tự động
    private Integer id;

    @Column(unique = true)
    @NotEmpty(message = "Username cannot be empty")
    private String numberTeacher;

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    
    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email invalid")
    private String email;

    @NotEmpty(message = "Phone cannot be empty")
    private String phone;

    @NotEmpty(message = "Major cannot be empty")
    private String major;

    // @NotEmpty(message = "Birthday cannot be empty")
    // private String birthday;
    private LocalDate birthday;
    
    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    // 1 GV thuoc 1 khoa 1 khoa co nhieu SV 
    private Faculty faculty;

    @NotNull(message = "Gender cannot be null")
    private Integer gender;
    
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
}

