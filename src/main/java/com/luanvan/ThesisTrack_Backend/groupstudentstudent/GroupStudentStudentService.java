package com.luanvan.ThesisTrack_Backend.groupstudentstudent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.luanvan.ThesisTrack_Backend.calenderthesis.CalenderThesis;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudentRepository;
import com.luanvan.ThesisTrack_Backend.student.StudentRepository;

import jakarta.validation.Valid;

@Service
public class GroupStudentStudentService {
    @Autowired
    private GroupStudentStudentRepository repository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GroupStudentRepository groupStudentRepository;
    public void addGroupStudentStudent(GroupStudentStudent groupStudentStudent) {
        if(repository.findByStudentId(groupStudentStudent.getStudent().getId()).isPresent()) {
            GroupStudentStudent groupStudentStudent2 = repository.findByStudentId(groupStudentStudent.getStudent().getId()).get();
            groupStudentStudent2.setGroupStudent(groupStudentRepository.findById(groupStudentStudent.getGroupStudent().getId()).orElseThrow(() -> new NotFoundException("Không tồn tại nhóm sinh vien này")));
            repository.save(groupStudentStudent2);
        }else {

        studentRepository.findById(groupStudentStudent.getStudent().getId()).orElseThrow(() -> new NotFoundException("Không tồn tại sinh viên này"));
        groupStudentRepository.findById(groupStudentStudent.getGroupStudent().getId()).orElseThrow(() -> new NotFoundException("Không tồn tại nhóm sinh vien này"));

        repository.save(groupStudentStudent);
        }
    }
      public List<GroupStudentStudent> getByGroupStudentId(Integer id) {
        return repository.findByGroupStudentId(id);
    }
}
