package com.luanvan.ThesisTrack_Backend.topic;

import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
// import com.luanvan.ThesisTrack_Backend.exception.ResourceNotFoundException;
import com.luanvan.ThesisTrack_Backend.subject.SubjectRepository;
import com.luanvan.ThesisTrack_Backend.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import com.luanvan.ThesisTrack_Backend.subject.Subject;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
//import com.luanvan.ThesisTrack_Backend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private TopicRepository topicRepository;
    // 4 cái import phía dưới ko biết có đúng hay không
    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository, TeacherRepository teacherRepository,
            SubjectRepository subjectRepository) {
        this.topicRepository = topicRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public List<Topic> getAllTopicsByTeacherId(Integer id) {

        return topicRepository.findByTeacherId(id);
    }

    // kiểm tra subject tồn tại không, teacher đó tồn tại không, kiểm tra tên đó đã
    // tồn tại không mới cho nhập vào
    public void saveTopic(Topic topic) {
        // Đây là cách đầu tiên kiểm tra id teacher này tồn tại không
        // Kiểm tra nếu rỗng thì đưa ra ngoại lệ
        Teacher teacher = teacherRepository.findById(topic.getTeacher().getId()).orElseThrow(
                () -> new NotFoundException("Không tồn tại giảng viên với id: " + topic.getTeacher().getId()));
        // cách 2
        Optional<Subject> subject = subjectRepository.findById(topic.getSubject().getId());
        Subject s = new Subject();
        if (subject.isPresent()) {
            s = subject.get();
        } else {
            throw new NotFoundException("Không tồn tại học phần với id: " + topic.getTeacher().getId());
        }

        // kiểm tra tên có trùng không
        Optional<Topic> existingTopic = topicRepository.findByName(topic.getName());
        if (existingTopic.isPresent()) {
            throw new IllegalArgumentException("Tên của đề tài này'" + topic.getName() + "' đã được sử dụng.");
        }

        // lưu lại sau khi kiểm tra
        topicRepository.save(topic);
    }

    public void deleteTopic(Integer topicId) {
        topicRepository.deleteById(topicId);
    }

    public void updateTopic(Integer topicId, String name) {
        // Tìm đề tài cần cập nhật
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy đề tài với id: " + topicId));
        List<Topic> topics = topicRepository.findByTeacherId(topic.getTeacher().getId());
        for (Topic t : topics) {
            if (name.equals(t.getName())) {
                throw new NotFoundException("Đề tài này tồn tại rồi");
            }
        }
        topic.setName(name);
        topicRepository.save(topic);
    }

}
