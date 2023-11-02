package com.luanvan.ThesisTrack_Backend.exception;


// lỗi 500
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
