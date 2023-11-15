package com.luanvan.ThesisTrack_Backend.calender;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.ThesisTrack_Backend.exception.DuplicateException;
import com.luanvan.ThesisTrack_Backend.exception.NotFoundException;

@Service
public class CalenderService {

    private final CalenderRepository calenderRepository;

    @Autowired
    public CalenderService(CalenderRepository calenderRepository) {
        this.calenderRepository = calenderRepository;
    }

    public List<Calender> getAllCalendarItems() {
        return calenderRepository.findAll();
    }
    // lấy thông tin theo id 
    public Calender getCalendarItemById(Integer id) {
        return calenderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy lịch với ID: " + id));
    }

    public List<Calender> getCalendarItemsByRoom(String room) {
        return calenderRepository.findByRoom(room);
    }

    public List<Calender> getCalendarItemsByWeek(Integer week) {
        return calenderRepository.findByWeek(week);
    }

    public List<Calender> getCalendarItemsByThu(String thu) {
        return calenderRepository.findByThu(thu);
    }

    public List<Calender> getCalendarItemsByDay(LocalDate day) {
        return calenderRepository.findByDay(day);
    }

    public List<Calender> findDuplicateCalendarItems(Integer week, String thu, LocalDate day, String room,
            String period) {
        return calenderRepository.findDuplicates(week, thu, day, room, period);
    }

    public void createCalendarItem(Calender calendar) {
        // Check for duplicates
        List<Calender> duplicates = calenderRepository.findDuplicates(
                calendar.getWeek(),
                calendar.getThu(),
                calendar.getDay(),
                calendar.getRoom(),
                calendar.getPeriod());

        if (duplicates.isEmpty()) {
            calenderRepository.save(calendar);
        } else {
            throw new DuplicateException("Lịch này trùng với lịch đã có.");
        }
    }

    public void deleteCalendarItemById(Integer id) {
        calenderRepository.deleteById(id);
    }

    // chỉnh sửa lại lịch
    public void updateCalendarItem(Integer itemId, Calender updatedCalendar) {
        // kiểm tra có tồn tại lịch này trước đó hay không
        Calender existingCalendar = calenderRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy lịch này"));

        // kiểm tra trùng lập thông tin nhập vào
        List<Calender> duplicates = findDuplicateCalendarItems(
            updatedCalendar.getWeek(),
            updatedCalendar.getThu(),
            updatedCalendar.getDay(),
            updatedCalendar.getRoom(),
            updatedCalendar.getPeriod()
        );

        // xử lý trùng lập
        duplicates.remove(existingCalendar);

        if (!duplicates.isEmpty()) {
            throw new DuplicateException("Lịch này đã trùng lập với lịch trước đó.");
        }

        // cập nhật lại các chỉnh sửa
        existingCalendar.setRoom(updatedCalendar.getRoom());
        existingCalendar.setWeek(updatedCalendar.getWeek());
        existingCalendar.setThu(updatedCalendar.getThu());
        existingCalendar.setDay(updatedCalendar.getDay());
        existingCalendar.setPeriod(updatedCalendar.getPeriod());
        existingCalendar.setNote(updatedCalendar.getNote());

        calenderRepository.save(existingCalendar);
    }
}
