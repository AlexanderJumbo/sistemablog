package com.blogapp.sistemablog.controller;

import com.blogapp.sistemablog.dto.Register.RegisterRequest;
import com.blogapp.sistemablog.dto.Register.RegisterResponse;
import com.blogapp.sistemablog.dto.User.UserResponse;
import com.blogapp.sistemablog.service.auth.AuthenticationService;
import com.blogapp.sistemablog.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getInfoUser(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getInfoUser(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerBlogger(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.registerBlogger(registerRequest));
    }


}
