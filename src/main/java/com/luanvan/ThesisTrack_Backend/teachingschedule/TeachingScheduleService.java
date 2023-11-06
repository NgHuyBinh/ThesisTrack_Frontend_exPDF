// package com.luanvan.ThesisTrack_Backend.teachingschedule;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class TeachingScheduleService {
//     private final TeachingScheduleRepository teachingScheduleRepository;

//     @Autowired
//     public TeachingScheduleService(TeachingScheduleRepository teachingScheduleRepository) {
//         this.teachingScheduleRepository = teachingScheduleRepository;
//     }
//     public TeachingSchedule createTeachingSchedule(TeachingSchedule teachingSchedule) {
//         if (teachingSchedule == null || teachingSchedule.getGroupStudent() == null) {
//             return null;
//         }

//         List<TeachingSchedule> conflictingSchedules = teachingScheduleRepository
//                 .findByGroupStudentIdAndRoomAndThuAndDay(
//                         teachingSchedule.getGroupStudent().getId(),
//                         teachingSchedule.getCalender().getId()
//                         // teachingSchedule.getRoom(),
//                         // teachingSchedule.getThu(),
//                         // teachingSchedule.getDay()
//                 );

//         for (TeachingSchedule existingSchedule : conflictingSchedules) {
//             if (isTimeConflict(existingSchedule, teachingSchedule)) {
//                 return null;
//             }
//         }

//         TeachingSchedule savedTeachingSchedule = teachingScheduleRepository.save(teachingSchedule);
//         if (savedTeachingSchedule == null) {
//             return null;
//         }

//         return savedTeachingSchedule;
//     }


//     public List<TeachingSchedule> getTeachingSchedulesByGroupStudentId(Integer groupStudentId) {
//         if (groupStudentId == null) {
//             // Xử lý lỗi khi thông tin không hợp lệ.
//             // Ví dụ: ném ra ngoại lệ hoặc trả về phản hồi lỗi.
//             return null;
//         }
//         return teachingScheduleRepository.findByGroupStudentId(groupStudentId);
//     }

//     private boolean isTimeConflict(TeachingSchedule schedule1, TeachingSchedule schedule2) {
//         // Định nghĩa logic kiểm tra trùng lịch dựa trên week và period ở đây.
//         // Trả về true nếu lịch trùng lịch, ngược lại trả về false.
//         return false; // Thay thế bằng logic kiểm tra thực tế.
//     }

//     private TeachingScheduleResponseDTO convertToResponseDTO(TeachingSchedule schedule) {
//         TeachingScheduleResponseDTO responseDTO = new TeachingScheduleResponseDTO();
//         // responseDTO.setRoom(schedule.getRoom());
//         // responseDTO.setWeek(schedule.getWeek());
//         // responseDTO.setThu(schedule.getThu());
//         // responseDTO.setDay(schedule.getDay());
//         // responseDTO.setPeriod(schedule.getPeriod());
//         // responseDTO.get
//         responseDTO.setSemester(schedule.getSemester());
//         responseDTO.setGroupStudent(schedule.getGroupStudent());
//         return responseDTO;
//     }

//     public List<TeachingScheduleResponseDTO> getAllTeachingSchedulesWithoutId() {
//         List<TeachingSchedule> teachingSchedules = teachingScheduleRepository.findAll();
//         List<TeachingScheduleResponseDTO> responseDTOs = new ArrayList<>();
//         for (TeachingSchedule schedule : teachingSchedules) {
//             TeachingScheduleResponseDTO responseDTO = convertToResponseDTO(schedule);
//             responseDTOs.add(responseDTO);
//         }
//         return responseDTOs;
//     }
// }