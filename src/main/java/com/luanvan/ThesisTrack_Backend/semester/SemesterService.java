package com.luanvan.ThesisTrack_Backend.semester;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    public Semester getCurrentSemester() {
        return semesterRepository.findCurrentSemester(LocalDate.now()).orElseThrow(() -> new NotFoundException("Chưa đến thời gian đăng ký giảng viên."));
        
     }

}
