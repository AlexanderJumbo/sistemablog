package com.blogapp.sistemablog.service.auth;

import com.blogapp.sistemablog.dto.Register.RegisterRequest;
import com.blogapp.sistemablog.dto.Register.RegisterResponse;
import com.blogapp.sistemablog.dto.User.UserResponse;
import com.blogapp.sistemablog.entity.security.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String author);

    UserResponse getInfoUser(Long userId);

    User registerBlogger(RegisterRequest registerRequest);
}
