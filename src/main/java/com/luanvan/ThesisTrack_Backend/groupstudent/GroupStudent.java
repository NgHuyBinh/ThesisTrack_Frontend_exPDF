package com.luanvan.ThesisTrack_Backend.groupstudent;

import com.luanvan.ThesisTrack_Backend.supervisor.Supervisor;
import com.luanvan.ThesisTrack_Backend.teachingschedule.TeachingSchedule;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Entity

// chia nhóm cho sinh viên 

public class GroupStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // mã nhóm sinh viên báo cáo
    private String code;
    
    // tên nhóm sinh viên báo cáo vd: Thầy Tuấn nhóm 1, nhóm 2,..
    private String name;

    // chỗ này có thể kết nối với bảng sinh viên viên không hay sinh viên kết nối với bảng này để thông báo lịch cho sinh viên 
    // hội đồng báo cáo, thêm rồi có thể chỉnh sửa được thay thế giảng viên khác được khi cần thiết khi có sự cố thay đổi lịch của giảng viên
//    @ManyToOne
//    @JoinColumn(name="supervisor_id")
//    private Supervisor supervisor;

//    private String status;

    // kết nối với với lịch báo cáo
//    @ManyToOne
//    @JoinColumn(name="teachingSchedule_id")
//    private TeachingSchedule teachingSchedule;

}
