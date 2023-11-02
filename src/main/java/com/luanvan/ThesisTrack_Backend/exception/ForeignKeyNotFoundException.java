package com.luanvan.ThesisTrack_Backend.exception;


// Khóa ngoại ko tồn tại
public class ForeignKeyNotFoundException extends RuntimeException {
    public ForeignKeyNotFoundException(String message) {
        super(message);
    }
}
