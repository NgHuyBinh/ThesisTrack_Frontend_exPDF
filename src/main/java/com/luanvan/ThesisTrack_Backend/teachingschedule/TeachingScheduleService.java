package com.luanvan.ThesisTrack_Backend.teachingschedule;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.luanvan.ThesisTrack_Backend.calender.Calender;
import com.luanvan.ThesisTrack_Backend.calender.CalenderRepository;
import com.luanvan.ThesisTrack_Backend.exception.AlreadyExistsException;
import com.luanvan.ThesisTrack_Backend.exception.InvalidValueException;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudent;
import com.luanvan.ThesisTrack_Backend.groupstudent.GroupStudentRepository;
import com.luanvan.ThesisTrack_Backend.semester.Semester;

import com.luanvan.ThesisTrack_Backend.semester.SemesterRepository;

import jakarta.transaction.Transactional;

import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;

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

    // lấy tất cả lịch
    public List<TeachingSchedule> getAllTeachingSchedules() {
        return teachingScheduleRepository.findAll();
    }
    // public List<TeachingScheduleResponseDTO> getAllTeachingSchedules() {
    //     List<TeachingSchedule> teachingSchedules = teachingScheduleRepository.findAll();

    //     List<TeachingScheduleResponseDTO> responseDTOs = new ArrayList<>();
    //     for (TeachingSchedule teachingSchedule : teachingSchedules) {
    //         TeachingScheduleResponseDTO responseDTO = new TeachingScheduleResponseDTO();
    //         responseDTO.setId(teachingSchedule.getId());
    //         responseDTO.setCalender(teachingSchedule.getCalender().getId());
    //         responseDTO.setSemester(teachingSchedule.getSemester().getId());
    //         responseDTO.setGroupStudent(teachingSchedule.getGroupStudent().getId());
    //         responseDTO.setStatus(teachingSchedule.getStatus());

    //         responseDTO.setRoom(teachingSchedule.getCalender().getRoom());
    //         responseDTO.setWeek(teachingSchedule.getCalender().getWeek());
    //         responseDTO.setThu(teachingSchedule.getCalender().getThu());
    //         responseDTO.setDay(teachingSchedule.getCalender().getDay());
    //         responseDTO.setPeriod(teachingSchedule.getCalender().getPeriod());
    //         responseDTO.setNote(teachingSchedule.getCalender().getNote());

    //         responseDTO.setSchoolYear(teachingSchedule.getSemester().getSchoolYear());
    //         responseDTO.setSemester_number(teachingSchedule.getSemester().getSemester());

    //         responseDTO.setCode(teachingSchedule.getGroupStudent().getCode());
    //         responseDTO.setName(teachingSchedule.getGroupStudent().getName());
    //         responseDTO.setTeacherId(teachingSchedule.getGroupStudent().getId());

    //         responseDTOs.add(responseDTO);
    //     }

    //     return responseDTOs;
    // }

    // hàm lấy lịch theo nhóm sinh viên
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
            responseDTO.setSemester_number(teachingSchedule.getSemester().getSemester());
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
        responseDTO.setTeacherId(teachingSchedule.getGroupStudent().getId());

        return responseDTO;
    }

    // hàm lấy thông tin theo nhóm sinh viên
    public List<TeachingScheduleResponseDTO> getAllTeachingSchedulesByGroupStudent(Integer groupStudentId) {
        List<TeachingSchedule> teachingSchedules = teachingScheduleRepository.findByGroupStudentId(groupStudentId);

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
            responseDTO.setSemester_number(teachingSchedule.getSemester().getSemester());

            responseDTO.setCode(teachingSchedule.getGroupStudent().getCode());
            responseDTO.setName(teachingSchedule.getGroupStudent().getName());
            responseDTO.setTeacherId(teachingSchedule.getGroupStudent().getId());

            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }

    // lấy thông tin theo id giảng viên
    public List<TeachingScheduleResponseDTO> getTeachingScheduleByTeacherId(Integer teacherId) {
        List<TeachingSchedule> teachingSchedules = teachingScheduleRepository
                .findTeachingScheduleByTeacherId(teacherId);

        if (teachingSchedules.isEmpty()) {
            // throw new NotFoundException("Không tìm thấy lịch giảng dạy cho giáo viên có
            // ID: " + teacherId);
            throw new AlreadyExistsException("Không tìm thấy lịch giảng dạy của giảng viên có id là: " + teacherId);
        }

        return teachingSchedules.stream()
                .map(this::mapTeachingScheduleToDTO)
                .collect(Collectors.toList());
    }

    private TeachingScheduleResponseDTO mapTeachingScheduleToDTO(TeachingSchedule teachingSchedule) {
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
        responseDTO.setSemester_number(teachingSchedule.getSemester().getSemester());

        responseDTO.setCode(teachingSchedule.getGroupStudent().getCode());
        responseDTO.setName(teachingSchedule.getGroupStudent().getName());
        responseDTO.setTeacherId(teachingSchedule.getGroupStudent().getId());
        return responseDTO;
    }

    // hàm chỉnh sửa lịch báo cáo cho nhóm sinh viên
    public void updateTeachingScheduleById(Integer id, TeachingScheduleResponseDTO requestDTO)
            throws InvalidValueException {
        // Lấy thông tin lịch giảng dạy từ ID
        TeachingSchedule existingTeachingSchedule = teachingScheduleRepository.findById(id)
                .orElseThrow(() -> new AlreadyExistsException("Không tìm thấy lịch báo cáo có ID: " + id));

        // Kiểm tra xem calender đã tồn tại trong các lịch giảng dạy khác chưa
        // if (teachingScheduleRepository.existsByCalenderAndIdNot(requestDTO.getCalender(), id)) {
        //     throw new InvalidValueException("Calender này đã được sử dụng trong lịch giảng dạy khác.");
        // }

        // Cập nhật các trường dữ liệu khác mà không thay đổi ID
        existingTeachingSchedule.setCalender(calenderRepository.findById(requestDTO.getCalender())
                .orElseThrow(() -> new NotFoundException("Calender không tồn tại")));
        existingTeachingSchedule.setSemester(semesterRepository.findById(requestDTO.getSemester())
                .orElseThrow(() -> new NotFoundException("Semester không tồn tại")));
        existingTeachingSchedule.setGroupStudent(groupStudentRepository.findById(requestDTO.getGroupStudent())
                .orElseThrow(() -> new NotFoundException("GroupStudent không tồn tại")));

        // Các cập nhật khác...

        // Lưu thay đổi
        teachingScheduleRepository.save(existingTeachingSchedule);
    }

    // hàm thêm lịch cho nhóm sinh viên
    public void addTeachingSchedule(TeachingSchedule teachingSchedule)  {
        Calender calender = calenderRepository.findById(teachingSchedule.getCalender().getId())
                .orElseThrow(() -> new InvalidValueException("Không tồn tại lịch với id " + teachingSchedule.getCalender().getId()));

        Semester semester = semesterRepository.findById(teachingSchedule.getSemester().getId())
                .orElseThrow(() -> new InvalidValueException("Không tồn tại học kỳ với id: " + teachingSchedule.getSemester().getId()));

        GroupStudent groupStudent = groupStudentRepository.findById(teachingSchedule.getGroupStudent().getId())
                .orElseThrow(() -> new InvalidValueException("Không tồn tại nhóm sinh viên với id " + teachingSchedule.getGroupStudent()
                .getId() + "của giảng viên " + teachingSchedule.getGroupStudent().getTeacher().getName()));

        // Kiểm tra xem GroupStudent đã được xếp lịch trước đó chưa
        if (teachingScheduleRepository.existsByGroupStudent(groupStudent)) {
            throw new InvalidValueException("Nhóm sinh viên này đã được xếp lịch trước đó.");
        }

        teachingScheduleRepository.save(teachingSchedule);
    }
}