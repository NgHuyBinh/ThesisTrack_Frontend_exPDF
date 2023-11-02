package com.luanvan.ThesisTrack_Backend.excel;

import org.apache.poi.ss.usermodel.*;
// import org.springframework.web.multipart.MultipartFile;

import com.luanvan.ThesisTrack_Backend.teacher.Teacher;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ExcelHelper {

    public static List<Teacher> excelToTeachers(InputStream is) {
        try {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            List<Teacher> teachers = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // Bỏ qua dòng đầu tiên (tiêu đề)
                    continue;
                }

                Teacher teacher = new Teacher();
                teacher.setNumberTeacher(row.getCell(1).getStringCellValue());
                teacher.setName(row.getCell(2).getStringCellValue());
                teacher.setAddress(row.getCell(3).getStringCellValue());
                teacher.setEmail(row.getCell(4).getStringCellValue());
                teacher.setPhone("0" + row.getCell(5).getStringCellValue());
                teacher.setMajor(row.getCell(6).getStringCellValue());
                teacher.setBirthday(row.getCell(7).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                teacher.setGender(row.getCell(9).getCellType() == CellType.NUMERIC ? (int) row.getCell(9).getNumericCellValue() : 0);

                teachers.add(teacher);
            }
            return teachers;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }
}
