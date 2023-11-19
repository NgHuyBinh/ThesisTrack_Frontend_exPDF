package com.luanvan.ThesisTrack_Backend.topic;

import com.luanvan.ThesisTrack_Backend.subject.Subject;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicResponseDTO {
    private Integer id;
    private String name;
    // private String subjectName;
    // private String teacherName;
    private Subject subject;
    private Teacher teacher;
    
    // public TopicResponseDTO(Topic topic) {
    //     this.id = topic.getId();
    //     this.name = topic.getName();
    //     this.subject = topic.getSubject().getId();
    //     this.teacher = topic.getTeacher().getId();
    // }
}
