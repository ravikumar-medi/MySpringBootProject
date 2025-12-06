package com.myproject.controller;

import com.myproject.dto.ChangePasswordRequestDTO;
import com.myproject.dto.LoginRequestDTO;
import com.myproject.model.Users;
import com.myproject.repository.UserRepo;
import com.myproject.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Users user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.register(user),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO request) {
        return userService.login(request);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequestDTO request) {
        return userService.changePassword(request);
    }

//    // CREATE USER API
//    @PostMapping("/createUser")
//    public Map<String, Object> createUser(@RequestBody UserCreateRequest request) {
//
//        // Service already returns Map<String,Object>
//        return service.createUser(request);
//    }
}
