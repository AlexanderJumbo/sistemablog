package com.blogapp.sistemablog.repository.auth;

import com.blogapp.sistemablog.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String author);
}
