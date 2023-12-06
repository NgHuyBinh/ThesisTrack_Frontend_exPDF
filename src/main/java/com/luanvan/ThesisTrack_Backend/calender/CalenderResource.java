package com.luanvan.ThesisTrack_Backend.calender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/calender")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201", "http://localhost:4202"  })

public class CalenderResource {
    private final CalenderService calenderService;

    @Autowired
    public CalenderResource(CalenderService calenderService) {
        this.calenderService = calenderService;
    }

    @GetMapping("/all")
    public List<Calender> getAllCalendarItems() {
        return calenderService.getAllCalendarItems();
    }

    @GetMapping("/room/{room}")
    public List<Calender> getCalendarItemsByRoom(@PathVariable String room) {
        return calenderService.getCalendarItemsByRoom(room);
    }

    @GetMapping("/week/{week}")
    public List<Calender> getCalendarItemsByWeek(@PathVariable Integer week) {
        return calenderService.getCalendarItemsByWeek(week);
    }


    @GetMapping("/day/{day}")
    public List<Calender> getCalendarItemsByDay(@PathVariable LocalDate day) {
        return calenderService.getCalendarItemsByDay(day);
    }

    // lấy thông tin theo id
    @GetMapping("/{itemId}")
    public ResponseEntity<Calender> getCalendarItemById(@PathVariable Integer itemId) {
        Calender calendar = calenderService.getCalendarItemById(itemId);
        return ResponseEntity.ok(calendar);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createCalendarItem(@RequestBody Calender calendar) {
        calenderService.createCalendarItem(calendar);
        // return calenderService.createCalendarItem(calendar);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public void deleteCalendarItem(@PathVariable Integer id) {
        calenderService.deleteCalendarItemById(id);
    }

    // chỉnh sửa lại lịch
    @PatchMapping("/update/{itemId}")
    public ResponseEntity<String> updateCalendarItem(
            @PathVariable Integer itemId,
            @RequestBody Calender updatedCalendar) {
        calenderService.updateCalendarItem(itemId, updatedCalendar);
        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
    }

}
