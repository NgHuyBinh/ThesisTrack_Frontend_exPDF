package com.luanvan.ThesisTrack_Backend.calenderthesis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.calender.CalenderRepository;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudentRepository;
import com.luanvan.ThesisTrack_Backend.groupstudentstudent.GroupStudentStudent;
import com.luanvan.ThesisTrack_Backend.student.StudentRepository;


@Service
public class CalenderThesisService {
    @Autowired
    private CalenderThesisRepository calenderThesisRepository;
    @Autowired
    private GroupStudentRepository groupStudentRepository;
    @Autowired
    private CalenderRepository calenderRepository;

    public void addCalenerThesis(CalenderThesis calenderThesis) {
         if(calenderThesisRepository.findByGroupStudentId(calenderThesis.getGroupStudent().getId()).isPresent()) {
            CalenderThesis calenderThesis2= calenderThesisRepository.findByGroupStudentId(calenderThesis.getGroupStudent().getId()).get();
            calenderThesis2.setCalender(calenderRepository.findById(calenderThesis.getCalender().getId()).orElseThrow(() -> new NotFoundException("Không tồn tại nhóm sinh vien này")));
            calenderThesis2.setGroupStudent(groupStudentRepository.findById(calenderThesis.getGroupStudent().getId()).orElseThrow(() -> new NotFoundException("Không tồn tại nhóm sinh vien này")));
            calenderThesisRepository.save(calenderThesis2);
        }else {

        calenderRepository.findById(calenderThesis.getCalender().getId()).orElseThrow(() -> new NotFoundException("Không tồn tại lịch này này"));
        groupStudentRepository.findById(calenderThesis.getGroupStudent().getId()).orElseThrow(() -> new NotFoundException("Không tồn tại nhóm sinh vien này"));

        calenderThesisRepository.save(calenderThesis);
        }
    }
    public CalenderThesis getByGroupStudentId(Integer id) {
        return calenderThesisRepository.findByGroupStudentId(id).orElseThrow(() -> new NotFoundException("Không tồn tại nhóm sinh viên này"));
    }
    public List<CalenderThesis> getByTeacherId(Integer id) {
        return calenderThesisRepository.findByGroupStudent_Teacher_Id(id);
    }
}
