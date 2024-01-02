package com.luanvan.ThesisTrack_Backend.recommend;

import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommedRepository extends JpaRepository<Recommend, Integer> {
    List<Recommend> findByTeacher1Id(Integer id);
    List<Recommend> findByTeacher2_IdOrTeacher3_Id(Integer teacher2Id, Integer teacher3Id);
}
