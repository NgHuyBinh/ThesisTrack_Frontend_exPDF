package com.luanvan.ThesisTrack_Backend.exception;

// thêm bị trùng dữ liệu
public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }
}
