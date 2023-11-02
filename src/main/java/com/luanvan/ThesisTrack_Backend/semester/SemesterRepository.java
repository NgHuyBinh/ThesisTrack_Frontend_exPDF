package com.luanvan.ThesisTrack_Backend.semester;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    @Query("SELECT s FROM Semester s " +
            "WHERE :currentDate BETWEEN s.startDate AND s.endDate")
    Optional<Semester> findSesmesterByCurrentDateBetweenStartDateAndEndDate(@Param("currentDate") LocalDate currentDate);
   
}
