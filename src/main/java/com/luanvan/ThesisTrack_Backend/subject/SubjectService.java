package com.luanvan.ThesisTrack_Backend.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public void addSubject(Subject subject) {
        if (subjectRepository.existsByCode(subject.getCode())) {
            throw new IllegalArgumentException("Subject with code " + subject.getCode() + " already exists");
        }
        subjectRepository.save(subject);
    }

    public Optional<Subject> getSubjectById(Integer id) {
        return subjectRepository.findById(id);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectByCode(String code) {
        return subjectRepository.findByCode(code);
    }
}
