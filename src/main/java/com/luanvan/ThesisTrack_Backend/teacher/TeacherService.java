package com.luanvan.ThesisTrack_Backend.teacher;

import java.util.Optional;
import java.util.stream.Collectors;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile; 

import com.luanvan.ThesisTrack_Backend.excel.ExcelHelper;

// import io.jsonwebtoken.io.IOException;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public Optional<Teacher> getTeacherByNoTeacher(String noTeacher) {
        return teacherRepository.findByNumberTeacher(noTeacher);
    }

    public Optional<Teacher> getTeacherById(Integer id) {
        return teacherRepository.findById(id);
    }

    // thêm dữ liệu bằng file excel
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    // lấy danh sách giảng viên theo khoa 
    public List<Teacher> getTeachersByFaculty(Integer facultyId) {
        return teacherRepository.findByFacultyId(facultyId);
    }

// public List<TeacherResponseDTO> getTeachersByFaculty(Integer facultyId) {
//     List<Teacher> teachers = teacherRepository.findByFacultyId(facultyId);
//     List<TeacherResponseDTO> responseDTOs = new ArrayList<>();

//     for (Teacher teacher : teachers) {
//         TeacherResponseDTO responseDTO = new TeacherResponseDTO();
//         responseDTO.setId(teacher.getId());
//         responseDTO.setNumberTeacher(teacher.getNumberTeacher());
//         responseDTO.setName(teacher.getName());
//         responseDTO.setFaculty(teacher.getFaculty().getName()); // Lấy tên khoa

//         responseDTOs.add(responseDTO);
//     }

//     return responseDTOs;
// }


    public boolean importTeachersFromExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<Teacher> teachers = ExcelHelper.excelToTeachers(inputStream);
            for (Teacher teacher : teachers) {
                Optional<Teacher> existingTeacher = getTeacherByNoTeacher(teacher.getNumberTeacher());
                if (existingTeacher.isPresent()) {
                    // Giảng viên đã tồn tại, cập nhật thông tin
                    Teacher updatedTeacher = existingTeacher.get();
                    updatedTeacher.setName(teacher.getName());
                    updatedTeacher.setAddress(teacher.getAddress());
                    updatedTeacher.setEmail(teacher.getEmail());
                    updatedTeacher.setPhone(teacher.getPhone());
                    updatedTeacher.setMajor(teacher.getMajor());
                    updatedTeacher.setBirthday(teacher.getBirthday());
                    updatedTeacher.setGender(teacher.getGender());
                    updateTeacher(updatedTeacher);
                } else {
                    // Giảng viên chưa tồn tại, thêm mới
                    addTeacher(teacher);
                }
            }
            return true; // Nếu nhập thành công
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi
        }
    }
    public boolean existsById(Integer teacherId) {
        return false;
    }
    public Teacher findById(Integer teacherId) {
        return null;
    }

}