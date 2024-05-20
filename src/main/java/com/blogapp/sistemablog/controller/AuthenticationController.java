package com.blogapp.sistemablog.controller;

import com.blogapp.sistemablog.dto.Auth.AuthenticationRequest;
import com.blogapp.sistemablog.dto.Auth.AuthenticationResponse;
import com.blogapp.sistemablog.service.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        authenticationService.logout(request);
        return ResponseEntity.status(HttpStatus.OK).body("Logout successfully");
    }



}
