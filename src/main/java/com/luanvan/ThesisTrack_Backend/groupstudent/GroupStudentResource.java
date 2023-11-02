package com.luanvan.ThesisTrack_Backend.groupstudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/groupStudent")
public class GroupStudentResource {
    private final GroupStudentService groupStudentService;

    @Autowired
    public GroupStudentResource(GroupStudentService groupStudentService) {
        this.groupStudentService = groupStudentService;
    }

}
