// package com.luanvan.ThesisTrack_Backend.feedback;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.time.LocalDate;

// import com.luanvan.ThesisTrack_Backend.student.Student;
// import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor

// public class FeedbackResponseDTO {
//     private Integer id;
//     private Student student;
//     private Teacher teacher;
//     private LocalDate sendDate;
//     private Integer status = 0;
//     private String note;
//     private Integer studentId; // Thêm trường studentId
//     private Integer teacherId; // Thêm trường teacherId

//     public Integer getId() {
//         return id;
//     }

//     public void setId(Integer id) {
//         this.id = id;
//     }

//     public Student getStudent() {
//         return student;
//     }

//     public void setStudent(Student student) {
//         this.student = student;
//     }

//     public Teacher getTeacher() {
//         return teacher;
//     }

//     public void setTeacher(Teacher teacher) {
//         this.teacher = teacher;
//     }

//     public LocalDate getSendDate() {
//         return sendDate;
//     }

//     public void setSendDate(LocalDate sendDate) {
//         this.sendDate = sendDate;
//     }

//     public Integer getStatus() {
//         return status;
//     }

//     public void setStatus(Integer status) {
//         this.status = status;
//     }

//     public String getNote() {
//         return note;
//     }

//     public void setNote(String note) {
//         this.note = note;
//     }

//     public Integer getStudentId() {
//         return studentId;
//     }

//     public void setStudentId(Integer studentId) {
//         this.studentId = studentId;
//     }

//     public Integer getTeacherId() {
//         return teacherId;
//     }

//     public void setTeacherId(Integer teacherId) {
//         this.teacherId = teacherId;
//     }
// }
