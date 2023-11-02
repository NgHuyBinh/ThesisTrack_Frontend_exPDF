package com.luanvan.ThesisTrack_Backend.subject;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByCode(String code);
    Optional<Subject> findByCode(String code);

    // Optional<Subject> findById(Integer id);
}
