package com.luanvan.ThesisTrack_Backend.topic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicResponseDTO {
    private Integer id;
    private String name;
    private String subjectName;
    private String teacherName;
    
    public TopicResponseDTO(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
        this.subjectName = topic.getSubject().getName();
        this.teacherName = topic.getTeacher().getName();
    }
}
