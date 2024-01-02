// package com.luanvan.ThesisTrack_Backend.groupstudent;

// import com.luanvan.ThesisTrack_Backend.student.Student;
// import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.util.List;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor

// // chia nhóm cho sinh viên
// public class GroupStudentRequestDTO {
//     @NotBlank(message = "Vui lòng nhập nhóm")
//     private String code;
//     @NotNull(message = "Vui lòng nhập id giảng viẻn")
//     private Integer teacherId;
//     private String note = new String();
//     @NotNull(message = "Vui lòng nhập id sinh viẻn")
//     private List<Integer> studentIds;
// }