// package com.luanvan.ThesisTrack_Backend.supervisor;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.luanvan.ThesisTrack_Backend.teacher.Teacher;
// import com.luanvan.ThesisTrack_Backend.teacher.TeacherRepository;

// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class SupervisorService {
//  private final SupervisorRepository supervisorRepository;
//     private final TeacherRepository teacherRepository;

//     @Autowired
//     public SupervisorService(SupervisorRepository supervisorRepository, TeacherRepository teacherRepository) {
//         this.supervisorRepository = supervisorRepository;
//         this.teacherRepository = teacherRepository;
//     }

//     public Supervisor createSupervisor(SupervisorResponseDTO supervisorResponseDTO) {
//         // Lấy thông tin giảng viên dựa trên ID của họ
//         Teacher thuKy = teacherRepository.findById(supervisorResponseDTO.getThuKy()).orElse(null);
//         Teacher chuTich = teacherRepository.findById(supervisorResponseDTO.getChuTich()).orElse(null);
//         Teacher uyVien = teacherRepository.findById(supervisorResponseDTO.getUyVien()).orElse(null);
    
//         // Kiểm tra xem các giảng viên có tồn tại và có khác nhau không
//         // Nếu bất kỳ giảng viên nào không tồn tại, hoặc nếu có sự trùng lặp giữa họ,
//         // bạn sẽ thực hiện xử lý lỗi tương ứng.
//         if (thuKy == null || chuTich == null || uyVien == null ||
//             thuKy.getId().equals(chuTich.getId()) || chuTich.getId().equals(uyVien.getId())
//             || uyVien.getId().equals(thuKy.getId())) {
//             // Xử lý lỗi, ví dụ: bạn có thể ném ra một ngoại lệ hoặc trả về một phản hồi
//             // lỗi.
//             // Thay thế điều này bằng xử lý lỗi cụ thể cho ứng dụng của bạn.
//             // Bạn cũng có thể thêm thông điệp lỗi để giúp xác định vấn đề.
//         }
    
//         // Tạo một đối tượng Supervisor
//         Supervisor supervisor = new Supervisor();
//         supervisor.setThuKy(thuKy);
//         supervisor.setChuTich(chuTich);
//         supervisor.setUyVien(uyVien);
//         supervisor.setStatus(supervisorResponseDTO.getStatus());
    
//         return supervisorRepository.save(supervisor);
//     }
    

//     public List<SupervisorResponseDTO> getAllSupervisors() {
//         List<Supervisor> supervisors = supervisorRepository.findAll();
//         return convertToResponseDTOList(supervisors);
//     }

//     public List<SupervisorResponseDTO> getSupervisorsByTeacherId(Integer teacherId) {
//         List<Supervisor> supervisors = supervisorRepository.findSupervisorsByTeacherId(teacherId);
//         return convertToResponseDTOList(supervisors);
//     }

//     public SupervisorResponseDTO convertToResponseDTO(Supervisor supervisor) {
//         return null;
//         // Thực hiện logic chuyển đổi để tạo SupervisorResponseDTO từ Supervisor entity.
//         // Trả về DTO.
//     }

//     private List<SupervisorResponseDTO> convertToResponseDTOList(List<Supervisor> supervisors) {
//         return supervisors.stream()
//             .map(this::convertToResponseDTO)
//             .collect(Collectors.toList());
//     }

//     // Implement other service methods and business logic as needed.
// }
