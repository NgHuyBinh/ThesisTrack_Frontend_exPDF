package com.luanvan.ThesisTrack_Backend.teachingschedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.calender.Calender;
import com.luanvan.ThesisTrack_Backend.calender.CalenderRepository;
import com.luanvan.ThesisTrack_Backend.exception.InvalidValueException;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudentRepository;
import com.luanvan.ThesisTrack_Backend.semester.Semester;

import com.luanvan.ThesisTrack_Backend.semester.SemesterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeachingScheduleService {

    private final TeachingScheduleRepository teachingScheduleRepository;
    private final CalenderRepository calenderRepository;
    private final SemesterRepository semesterRepository;
    private final GroupStudentRepository groupStudentRepository;

    @Autowired
    public TeachingScheduleService(TeachingScheduleRepository teachingScheduleRepository,
            CalenderRepository calenderRepository,
            SemesterRepository semesterRepository,
            GroupStudentRepository groupStudentRepository) {
        this.teachingScheduleRepository = teachingScheduleRepository;
        this.calenderRepository = calenderRepository;
        this.semesterRepository = semesterRepository;
        this.groupStudentRepository = groupStudentRepository;
    }

    public List<TeachingScheduleResponseDTO> getAllTeachingSchedules() {
        List<TeachingSchedule> teachingSchedules = teachingScheduleRepository.findAll();

        List<TeachingScheduleResponseDTO> responseDTOs = new ArrayList<>();
        for (TeachingSchedule teachingSchedule : teachingSchedules) {
            TeachingScheduleResponseDTO responseDTO = new TeachingScheduleResponseDTO();
            responseDTO.setId(teachingSchedule.getId());
            responseDTO.setCalender(teachingSchedule.getCalender().getId());
            responseDTO.setSemester(teachingSchedule.getSemester().getId());
            responseDTO.setGroupStudent(teachingSchedule.getGroupStudent().getId());
            responseDTO.setStatus(teachingSchedule.getStatus());

            responseDTO.setRoom(teachingSchedule.getCalender().getRoom());
            responseDTO.setWeek(teachingSchedule.getCalender().getWeek());
            responseDTO.setThu(teachingSchedule.getCalender().getThu());
            responseDTO.setDay(teachingSchedule.getCalender().getDay());
            responseDTO.setPeriod(teachingSchedule.getCalender().getPeriod());
            responseDTO.setNote(teachingSchedule.getCalender().getNote());

            responseDTO.setSchoolYear(teachingSchedule.getSemester().getSchoolYear());
            responseDTO.setSemester_number(teachingSchedule.getSemester().getSemesterNumber());

            responseDTO.setCode(teachingSchedule.getGroupStudent().getCode());
            responseDTO.setName(teachingSchedule.getGroupStudent().getName());

            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }


    public TeachingScheduleResponseDTO getTeachingScheduleById(Integer id) {
        TeachingSchedule teachingSchedule = teachingScheduleRepository.findById(id).orElse(null);
        if (teachingSchedule == null) {
            return null;
        }
    
        TeachingScheduleResponseDTO responseDTO = new TeachingScheduleResponseDTO();
        responseDTO.setId(teachingSchedule.getId());
        responseDTO.setCalender(teachingSchedule.getCalender().getId());
        
        // Check if semester is not null before setting its properties
        if (teachingSchedule.getSemester() != null) {
            responseDTO.setSemester(teachingSchedule.getSemester().getId());
            responseDTO.setSchoolYear(teachingSchedule.getSemester().getSchoolYear());
            responseDTO.setSemester_number(teachingSchedule.getSemester().getSemesterNumber());
        }
    
        responseDTO.setGroupStudent(teachingSchedule.getGroupStudent().getId());
        responseDTO.setStatus(teachingSchedule.getStatus());
    
        // Set other properties from Calender and GroupStudent
        responseDTO.setRoom(teachingSchedule.getCalender().getRoom());
        responseDTO.setWeek(teachingSchedule.getCalender().getWeek());
        responseDTO.setThu(teachingSchedule.getCalender().getThu());
        responseDTO.setDay(teachingSchedule.getCalender().getDay());
        responseDTO.setPeriod(teachingSchedule.getCalender().getPeriod());
        responseDTO.setNote(teachingSchedule.getCalender().getNote());
    
        responseDTO.setCode(teachingSchedule.getGroupStudent().getCode());
        responseDTO.setName(teachingSchedule.getGroupStudent().getName());
    
        return responseDTO;
    }
    

    public void addTeachingSchedule(TeachingScheduleResponseDTO requestDTO) throws InvalidValueException {
        // Kiểm tra xem Calender, Semester và GroupStudent tồn tại
        Integer calenderId = requestDTO.getCalender();
        Integer semesterId = requestDTO.getSemester();
        Integer groupStudentId = requestDTO.getGroupStudent();

        Calender calender = calenderRepository.findById(calenderId)
                .orElseThrow(() -> new InvalidValueException("Calender không tồn tại"));

        Semester semester = semesterRepository.findById(semesterId)
                .orElseThrow(() -> new InvalidValueException("Semester không tồn tại"));

        GroupStudent groupStudent = groupStudentRepository.findById(groupStudentId)
                .orElseThrow(() -> new InvalidValueException("GroupStudent không tồn tại"));

        // Kiểm tra xem GroupStudent đã được xếp lịch trước đó chưa
        if (teachingScheduleRepository.existsByGroupStudent(groupStudent)) {
            throw new InvalidValueException("Nhóm sinh viên này đã được xếp lịch trước đó.");
        }

        // Nếu tất cả đều tồn tại, tiến hành lưu dữ liệu
        TeachingSchedule newTeachingSchedule = new TeachingSchedule();
        newTeachingSchedule.setCalender(calender);
        newTeachingSchedule.setSemester(semester);
        newTeachingSchedule.setGroupStudent(groupStudent);

        newTeachingSchedule = teachingScheduleRepository.save(newTeachingSchedule);
    }
}