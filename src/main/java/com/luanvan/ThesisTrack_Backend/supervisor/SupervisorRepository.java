// package com.luanvan.ThesisTrack_Backend.supervisor;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
// import org.springframework.stereotype.Repository;

// import java.util.List;

// @Repository
// public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {

//     @Query("SELECT new com.luanvan.ThesisTrack_Backend.supervisor.SupervisorResponseDTO(s.id, s.thuKy.name, s.chuTich.name, s.uyVien.name, s.status) FROM Supervisor s")
//     List<SupervisorResponseDTO> getAllSupervisors();

//     List<Supervisor> findByThuKyIdOrChuTichIdOrUyVienId(Integer teacherId1, Integer teacherId2, Integer teacherId3);

//     @Query("SELECT s FROM Supervisor s WHERE s.thuKy.id = :teacherId OR s.chuTich.id = :teacherId OR s.uyVien.id = :teacherId")
//     List<Supervisor> findSupervisorsByTeacherId(Integer teacherId);
// }