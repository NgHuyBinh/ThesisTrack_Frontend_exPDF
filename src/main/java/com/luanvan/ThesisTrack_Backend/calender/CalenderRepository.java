package com.luanvan.ThesisTrack_Backend.calender;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalenderRepository extends JpaRepository <Calender, Integer> {
    
    // kiểm tra trùng dữ liệu
    @Query("SELECT c FROM Calender c WHERE c.week = :week AND c.day = :day AND c.room = :room AND c.period = :period")
    List<Calender> findDuplicates(
        @Param("week") Integer week,
        // @Param("thu") String thu,
        @Param("day") LocalDate day,
        @Param("room") String room,
        @Param("period") String period
    );

    List<Calender> findByRoom(String room);

    List<Calender> findByWeek(Integer week);

    // List<Calender> findByThu(String thu);

    List<Calender> findByDay(LocalDate day);

    Optional<Calender> findById(Integer id);
}
