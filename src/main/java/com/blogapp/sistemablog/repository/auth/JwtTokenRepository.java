package com.blogapp.sistemablog.repository.auth;

import com.blogapp.sistemablog.entity.security.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    Optional<JwtToken> findByToken(String jwt);
}
