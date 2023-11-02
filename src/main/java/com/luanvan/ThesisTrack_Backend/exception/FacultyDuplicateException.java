package com.luanvan.ThesisTrack_Backend.exception;

// thêm trùng khoa
public class FacultyDuplicateException extends DuplicateException {
    public FacultyDuplicateException(String message) {
        super(message);
    }
}
