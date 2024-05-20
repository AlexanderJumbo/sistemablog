package com.blogapp.sistemablog.controller;

import com.blogapp.sistemablog.dto.Followers.FollowerRequest;
import com.blogapp.sistemablog.dto.User.UserResponse;
import com.blogapp.sistemablog.repository.auth.UserRepository;
import com.blogapp.sistemablog.service.FollowerService;
import com.blogapp.sistemablog.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/followers")
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @PutMapping
    public ResponseEntity<String> createFollowUpRequest(@RequestBody FollowerRequest followerRequest){
        return ResponseEntity.status(HttpStatus.OK).body(followerService.createFollowUpRequest(followerRequest));
    }

}
