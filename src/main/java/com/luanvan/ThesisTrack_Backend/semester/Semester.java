package com.luanvan.ThesisTrack_Backend.semester;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Không để trống năm học")
    private String schoolYear;

    @NotNull(message = "Không để trống học kỳ")
    private Integer semester; 

    @NotNull(message = "Không để trống ngày bắt đầu")
    private LocalDate startDate;
    @NotNull(message = "Không để trống ngày kết thúc")
    private LocalDate endDate;
}
