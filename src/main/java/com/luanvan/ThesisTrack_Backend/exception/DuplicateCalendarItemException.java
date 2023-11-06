package com.luanvan.ThesisTrack_Backend.exception;


// xử lý của lịch 
public class DuplicateCalendarItemException extends RuntimeException {
    public DuplicateCalendarItemException(String message) {
        super(message);
    }
}