package com.luanvan.ThesisTrack_Backend.groupstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupStudentService {
    private final GroupStudentRepository groupStudentRepository;

    @Autowired
    public GroupStudentService(GroupStudentRepository groupStudentRepository) {
        this.groupStudentRepository = groupStudentRepository;
    }

    // Thêm các phương thức xử lý dữ liệu `GroupStudent` tại đây.
}
