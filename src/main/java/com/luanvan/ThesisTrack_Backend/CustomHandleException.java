package com.luanvan.ThesisTrack_Backend;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.luanvan.ThesisTrack_Backend.exception.AlreadyExistsException;

import com.luanvan.ThesisTrack_Backend.exception.InvalidValueException;
import com.luanvan.ThesisTrack_Backend.exception.DuplicateException;
import com.luanvan.ThesisTrack_Backend.exception.FacultyDuplicateException;
import com.luanvan.ThesisTrack_Backend.exception.ForeignKeyNotFoundException;
import com.luanvan.ThesisTrack_Backend.exception.InternalServerErrorException;
// import com.luanvan.ThesisTrack_Backend.exception.UsernameAlreadyExistsException;
import com.luanvan.ThesisTrack_Backend.exception.email.EmailException;
import com.luanvan.ThesisTrack_Backend.exception.email.EmailSendingException;
import com.luanvan.ThesisTrack_Backend.message.ErrorMessage;

@RestControllerAdvice
public class CustomHandleException {
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> handleUsernameAlreadyExistsException(AlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorMessage errorMessage = new ErrorMessage("Validation failed", details);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<ErrorMessage> handleInvalidValueException(InvalidValueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(ex.getMessage()));
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorMessage> handleDuplicateException(DuplicateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(ex.getMessage()));
    }

    // @ExceptionHandler(ForeignKeyNotFoundException.class)
    // public ResponseEntity<ErrorMessage>
    // handleForeignKeyNotFoundException(ForeignKeyNotFoundException ex) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
    // ErrorMessage(ex.getMessage()));
    // }

    // @ExceptionHandler(InternalServerErrorException.class)
    // public ResponseEntity<ErrorMessage>
    // handleInternalServerErrorException(InternalServerErrorException ex) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
    // ErrorMessage("Internal Server Error"));
    // }

    // @ExceptionHandler(FacultyDuplicateException.class)
    // public ResponseEntity<ErrorMessage>
    // handleFacultyDuplicateException(FacultyDuplicateException ex) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
    // ErrorMessage(ex.getMessage()));
    // }

    // @ExceptionHandler(EmailException.class)
    // public ResponseEntity<ErrorMessage> handleEmailException(EmailException ex) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
    // ErrorMessage(ex.getMessage()));
    // }

    // @ExceptionHandler(EmailSendingException.class)
    // public ResponseEntity<ErrorMessage>
    // handleEmailSendingException(EmailSendingException ex) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
    // ErrorMessage(ex.getMessage()));
    // }

    // @ExceptionHandler(UsernameAlreadyExistsException.class)
    // public ResponseEntity<ErrorMessage>
    // handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
    // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
    // ErrorMessage(ex.getMessage()));
    // }
}
