package com.luanvan.ThesisTrack_Backend.faculty;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.DuplicateException;

// import com.luanvan.ThesisTrack_Backend.exception.FacultyCodeAlreadyExistsException;
@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public void createFaculty(Faculty faculty) {
        if (facultyRepository.existsByCode(faculty.getCode())) {
            throw new DuplicateException("Mã khoa đã tồn tại.");
        }
        facultyRepository.save(faculty);
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
}