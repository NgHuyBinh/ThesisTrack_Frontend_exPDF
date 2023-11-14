package com.luanvan.ThesisTrack_Backend.markteacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.AlreadyExistsException;
import com.luanvan.ThesisTrack_Backend.exception.InvalidValueException;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;
import com.luanvan.ThesisTrack_Backend.semester.Semester;
import com.luanvan.ThesisTrack_Backend.semester.SemesterRepository;
import com.luanvan.ThesisTrack_Backend.student.Student;
import com.luanvan.ThesisTrack_Backend.student.StudentRepository;
import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
import com.luanvan.ThesisTrack_Backend.teacher.TeacherRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class MarkService {

    private final MarkRepository markRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SemesterRepository semesterRepository;

    @Autowired
    public MarkService(MarkRepository markRepository, StudentRepository studentRepository,
            TeacherRepository teacherRepository, SemesterRepository semesterRepository) {
        this.markRepository = markRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.semesterRepository = semesterRepository;
    }

    public List<Mark> getAllMarks() {
        return markRepository.findAll();
    }

    public Mark getMarkById(Integer id) {
        return markRepository.findById(id).orElse(null);
    }

    public void createMark(Mark mark) {
        Float sumMark = (float) 0.0;
        // Kiểm tra xem sinh viên có tồn tại không
        Student student = studentRepository.findById(mark.getStudent().getId())
                .orElseThrow(
                        () -> new NotFoundException("Không tồn tại sinh viên với id " + mark.getStudent().getId()));

        // Kiểm tra xem giảng viên có tồn tại không
        Teacher teacher = teacherRepository.findById(mark.getTeacher().getId())
                .orElseThrow(
                        () -> new NotFoundException("Không tồn tại giảng viên với id " + mark.getTeacher().getId()));

        // Kiểm tra xem học kỳ có tồn tại không
        Semester semester = semesterRepository.findById(mark.getSemester().getId())
                .orElseThrow(() -> new NotFoundException("Không tồn tại học kỳ với id " + mark.getSemester().getId()));

        // Kiểm tra thời gian nhập điểm
        if (LocalDate.now().isBefore(semester.getStartDate())
                || LocalDate.now().isAfter(semester.getEndDate())) {
            throw new InvalidValueException("Chưa đến thời gian nhập điểm.");
        }

        // Kiểm tra đã có điểm của sinh viên trong học kỳ này chưa
        List<Mark> marks = markRepository.findByStudentIdAndSemesterId(mark.getStudent().getId(),
                mark.getSemester().getId());
        if (!marks.isEmpty()) {
            throw new AlreadyExistsException("Sinh viên này đã chấm điểm rồi");
        }

        // kiểm tra điểm phần 1 nội dung
        // kiểm tra điểm hình thức quyển báo cáo 1.1
        if (mark.getMark11() <= 0 || mark.getMark11() > 0.5) {
            throw new InvalidValueException(
                    "Vui lòng chấm điểm hình thức quyển báo cáo lớn hơn 0 đến bé hơn hoặc bằng 0.5 điểm.");
        } else {
            sumMark += mark.getMark11();
        }

        // kiểm tra điểm tóm tắt Tiếng Việt và Tiếng Anh 1.2
        if (mark.getMark12() <= 0 || mark.getMark12() > 0.5) {
            throw new InvalidValueException(
                    "Vui lòng chấm điểm tóm tắt Tiếng Việt và Tiếng Anh lớn hơn 0 đến bé hơn hoặc bằng 0.5 điểm.");
        } else {
            sumMark += mark.getMark12();
        }

        // kiểm tra điểm phần giới thiệu 1.3
        if (mark.getMark13() <= 0 || mark.getMark13() > 1) {
            throw new InvalidValueException(
                    "Vui lòng chấm điểm phần giới thiệu lớn hơn 0 đến bé hơn hoặc bằng 1 điểm.");
        } else {
            sumMark += mark.getMark13();
        }

        // kiểm tra điểm mô tả bài toán 1.4
        if (mark.getMark14() <= 0 || mark.getMark14() > 1.5) {
            throw new InvalidValueException(
                    "Vui lòng chấm điểm mô tả bài toán lớn hơn 0 đến bé hơn hoặc bằng 1.5 điểm.");
        } else {
            sumMark += mark.getMark14();
        }

        // kiểm tra điểm thiết kế và cài đặt giải pháp 1.5
        if (mark.getMark15() <= 0 || mark.getMark15() > 2) {
            throw new InvalidValueException(
                    "Vui lòng chấm điểm thiết kế và cài đặt giải pháp hơn 0 đến bé hơn hoặc bằng 2 điểm.");
        } else {
            sumMark += mark.getMark15();
        }

        // kiểm tra điểm kiểm thử và đánh giá 2
        if (mark.getMark2() <= 0 || mark.getMark2() > 2) {
            throw new InvalidValueException(
                    "Vui lòng chấm điểm kiểm thử và đánh giá hơn 0 đến bé hơn hoặc bằng 2 điểm.");
        } else {
            sumMark += mark.getMark2();
        }

        // kiểm tra điểm phần kết luận 2.1
        if (mark.getMark21() <= 0 || mark.getMark21() > 0.5) {
            throw new InvalidValueException(
                    "Vui lòng chấm điểm phần kết luận lớn hơn 0 đến bé hơn hoặc bằng 0.5 điểm.");
        } else {
            sumMark += mark.getMark21();
        }

        // kiểm tra điểm trình bày báo cáo 2.2
        if (mark.getMark22() <= 0 || mark.getMark22() > 1) {
            throw new InvalidValueException(
                    "Vui lòng chấm điểm trình bày bái cáo lớn hơn 0 đến bé hơn hoặc bằng 1 điểm.");
        } else {
            sumMark += mark.getMark22();
        }

        // kiểm tra điểm trả lời chất vấn 2.3
        if (mark.getMark23() <= 0 || mark.getMark23() > 1) {
            throw new InvalidValueException(
                    "Vui lòng chấm điểm trả lời chất vấn hơn 0 đến bé hơn hoặc bằng 1 điểm.");
        } else {
            sumMark += mark.getMark23();
        }
        mark.setSumMark(sumMark);

        markRepository.save(mark);
    }

    // public Mark updateMark(Integer id, Mark updatedMark) {
    // // Kiểm tra xem mark có tồn tại không
    // Mark existingMark = markRepository.findById(id).orElse(null);
    // if (existingMark != null) {
    // // Cập nhật các thông tin cần thiết
    // existingMark.setMark11(updatedMark.getMark11());
    // existingMark.setNote11(updatedMark.getNote11());
    // // Cập nhật thêm các trường khác tùy ý

    // // Lưu lại vào cơ sở dữ liệu
    // return markRepository.save(existingMark);
    // }
    // return null; // Hoặc bạn có thể ném ra một exception tùy theo yêu cầu
    // }

    public void deleteMark(Integer id) {
        markRepository.deleteById(id);
    }

    public List<Mark> getMarksByTeacherId(Integer teacherId) {
        return markRepository.findByTeacherId(teacherId);
    }

    public List<Mark> getMarksByStudentId(Integer studentId) {
        return markRepository.findByStudentId(studentId);
    }

    public List<Mark> getMarksBySemesterId(Integer semesterId) {
        return markRepository.findBySemesterId(semesterId);
    }
}
