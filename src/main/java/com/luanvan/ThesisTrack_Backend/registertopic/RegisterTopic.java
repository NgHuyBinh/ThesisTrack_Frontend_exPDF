package com.luanvan.ThesisTrack_Backend.registertopic;

import com.luanvan.ThesisTrack_Backend.semester.Semester;
import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.topic.Topic;

// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RegisterTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //Sinh viên nào đăng ký
    @NotNull(message = "ID sinh viên không được null")
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    //Đăng ký đề tài nào //có 38 thì ko  có 46
//    @ManyToOne(optional = true) // Có thể null để cho sinh viên tự đăng ký
    @ManyToOne
    @JoinColumn(name="topic_id", nullable = true)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name="semester_id")
    private Semester semester;

    private String topicName = "";
    
    private Integer status = 0;

    private String reason = new String();

}