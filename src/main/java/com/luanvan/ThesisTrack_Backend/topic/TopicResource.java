package com.luanvan.ThesisTrack_Backend.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/topics")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201", "http://localhost:4202" })
public class TopicResource {

    @Autowired
    private TopicService topicService;

    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

     @GetMapping("/teacher/{teacherId}")
     public ResponseEntity<List<Topic>> getTopicsByTeacher(@PathVariable Integer teacherId) {
         List<Topic> topics = topicService.getAllTopicsByTeacherId(teacherId);
         return ResponseEntity.status(HttpStatus.OK).body(topics);
     }

     @PostMapping("/add")
     public ResponseEntity<Void> addTopic(@RequestBody Topic topic) {
         topicService.saveTopic(topic);
         return ResponseEntity.status(HttpStatus.CREATED).build();
     }

    @DeleteMapping("/{topicId}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Integer topicId) {
        topicService.deleteTopic(topicId);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatedTopic(@PathVariable Integer id, @RequestBody String name){
        topicService.updateTopic(id, name);
        return ResponseEntity.noContent().build();
    }

}
