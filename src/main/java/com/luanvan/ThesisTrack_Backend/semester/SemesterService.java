package com.luanvan.ThesisTrack_Backend.semester;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.DuplicateException;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    public Semester getCurrentSemester() {
        return semesterRepository.findCurrentSemester(LocalDate.now())
                .orElseThrow(() -> new NotFoundException("Chưa đến thời gian đăng ký giảng viên."));

    }

    public void createSemester(Semester semester) {
        // Kiểm tra sự trùng lặp
        if (semesterRepository.existsBySchoolYearAndSemester(semester.getSchoolYear(), semester.getSemester())) {
            throw new DuplicateException("Học kỳ đã tồn tại.");
        }
        // Thêm học kỳ
        semesterRepository.save(semester);
    }

    public void updateSemester(Integer id, Semester updatedSemester) {
        // Kiểm tra xem học kỳ có tồn tại không
        Semester existingSemester = semesterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy học kỳ với ID " + id));

        // Kiểm tra sự trùng lặp nếu thay đổi schoolYear hoặc semester
        if (!existingSemester.getSchoolYear().equals(updatedSemester.getSchoolYear())
                || !existingSemester.getSemester().equals(updatedSemester.getSemester())) {
            if (semesterRepository.existsBySchoolYearAndSemester(updatedSemester.getSchoolYear(),
                    updatedSemester.getSemester())) {
                throw new DuplicateException("Học kỳ đã tồn tại.");
            }
        }

        // Cập nhật thông tin học kỳ
        existingSemester.setSchoolYear(updatedSemester.getSchoolYear());
        existingSemester.setSemester(updatedSemester.getSemester());
        existingSemester.setStartDate(updatedSemester.getStartDate());
        existingSemester.setEndDate(updatedSemester.getEndDate());
        existingSemester.setRtStartDate(updatedSemester.getRtStartDate());
        existingSemester.setRtEndDay(updatedSemester.getRtEndDay());

        semesterRepository.save(existingSemester);
    }
}
