package com.luanvan.ThesisTrack_Backend.groupstudentstudent;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupStudentStudentRepository extends JpaRepository<GroupStudentStudent, Integer> {
    Optional<GroupStudentStudent> findByStudentId(Integer id);
    List<GroupStudentStudent> findByGroupStudentId(Integer id);
}
