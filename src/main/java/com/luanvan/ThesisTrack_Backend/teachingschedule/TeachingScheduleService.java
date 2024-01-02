// package com.luanvan.ThesisTrack_Backend.teachingschedule;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.luanvan.ThesisTrack_Backend.addgroupstudent.AddGroupStudent;
// import com.luanvan.ThesisTrack_Backend.addgroupstudent.AddGroupStudentRepository;
// import com.luanvan.ThesisTrack_Backend.calender.Calender;
// import com.luanvan.ThesisTrack_Backend.calender.CalenderRepository;

// import com.luanvan.ThesisTrack_Backend.semester.Semester;

// import com.luanvan.ThesisTrack_Backend.semester.SemesterRepository;

// import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;

// import java.util.List;

// @Service
// public class TeachingScheduleService {

//     private TeachingScheduleRepository teachingScheduleRepository;
//     private CalenderRepository calenderRepository;
//     private SemesterRepository semesterRepository;
//     private AddGroupStudentRepository addGroupStudentRepository;

//     @Autowired
//     public TeachingScheduleService(
//         TeachingScheduleRepository teachingScheduleRepository,
//         CalenderRepository calenderRepository,
//         SemesterRepository semesterRepository,
//         AddGroupStudentRepository addGroupStudentRepository
//     ){
//         this.teachingScheduleRepository = teachingScheduleRepository;
//         this.semesterRepository = semesterRepository;
//         this.calenderRepository = calenderRepository;
//         this.addGroupStudentRepository = addGroupStudentRepository;

//     }


//     public void addTeachingSchedule(TeachingSchedule teachingSchedule) {
//         Calender calender = calenderRepository.findById(teachingSchedule.getCalender().getId())
//                 .orElseThrow(() -> new NotFoundException("Không tồn tại lịch vói id:" + teachingSchedule.getCalender().getId()));

//         Semester semester = semesterRepository.findById(teachingSchedule.getSemester().getId())
//                 .orElseThrow(() -> new NotFoundException("Không tồn tại học lỳ với id: " + teachingSchedule.getSemester().getId()));

//         AddGroupStudent addGroupStudent = addGroupStudentRepository.findById(teachingSchedule.getAddGroupStudent().getId())
//                 .orElseThrow(() -> new NotFoundException("Không tồn tại xếp lịch sinh viên này với id:" + teachingSchedule.getAddGroupStudent().getId()));

//         teachingSchedule.setCalender(calender);
//         teachingSchedule.setSemester(semester);
//         teachingSchedule.setAddGroupStudent(addGroupStudent);
//         teachingSchedule.setStatus(0);

//         teachingScheduleRepository.save(teachingSchedule);
//     }

//     public List<TeachingSchedule> getAllTeachingSchedules() {
//         return teachingScheduleRepository.findAll();
//     }

//     public List<TeachingSchedule> getTeachingSchedulesByCalenderId(Integer calenderId) {
//         return teachingScheduleRepository.findByCalender_Id(calenderId);
//     }

//     public List<TeachingSchedule> getTeachingSchedulesByAddGroupStudentId(Integer addGroupStudentId) {
//         return teachingScheduleRepository.findByAddGroupStudent_Id(addGroupStudentId);
//     }
// }