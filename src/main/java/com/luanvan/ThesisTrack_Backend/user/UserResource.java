package com.luanvan.ThesisTrack_Backend.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.ThesisTrack_Backend.authentication.AuthResponseDTO;
import com.luanvan.ThesisTrack_Backend.message.SuccessMessage;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201"})

@RequestMapping("api/v1/user")
public class UserResource {
    
    private UserService userService;

    @Autowired //tiêm phụ thuộc vào
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<SuccessMessage> register( @Valid @RequestBody User user) {
        userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessMessage("Account successfully created"));
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody User loginDTO){ 
        String token = userService.login(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponseDTO(token));
    }

    @GetMapping("role/{username}")
    public ResponseEntity<List<String>> getRoleByUsername(@PathVariable("username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getRoleByUsername(username));
    }
    // @GetMapping("hello")
    // public String hello() {
    //     return "hi";
    // }
}
