package com.luanvan.ThesisTrack_Backend.supervisor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {

}