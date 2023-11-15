package com.luanvan.ThesisTrack_Backend.faculty;

// import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
   boolean existsByCode(String code);

//    Optional<Faculty> finAllfaculty(Integer faculty);
}
