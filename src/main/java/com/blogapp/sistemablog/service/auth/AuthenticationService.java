package com.blogapp.sistemablog.service.auth;

import com.blogapp.sistemablog.dto.Auth.AuthenticationRequest;
import com.blogapp.sistemablog.dto.Auth.AuthenticationResponse;
import com.blogapp.sistemablog.dto.Register.RegisterRequest;
import com.blogapp.sistemablog.dto.Register.RegisterResponse;
import com.blogapp.sistemablog.entity.security.JwtToken;
import com.blogapp.sistemablog.entity.security.User;
import com.blogapp.sistemablog.repository.auth.JwtTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    public RegisterResponse registerBlogger(RegisterRequest registerRequest) {
        User user = userService.registerBlogger(registerRequest);
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        saveUserToken(user, jwt);

        return mapRegisterResponse(user, jwt);
    }

    private static RegisterResponse mapRegisterResponse(User user, String jwt) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setName(user.getName());
        registerResponse.setUsername(user.getUsername());
        registerResponse.setRole(user.getRole().getName());
        registerResponse.setJwt(jwt);
        return registerResponse;
    }

    private Map<String, Object> generateExtraClaims(User user) {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().getName());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            authenticationRequest.getUsername(), authenticationRequest.getPassword()
        );

        authenticationManager.authenticate(authentication);

        User user = userService.findByUsername(authenticationRequest.getUsername()).get();
        System.out.println("user " + user);

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        saveUserToken(user, jwt);

        return mapDTO(jwt);
    }

    private void saveUserToken(User user, String jwt) {

        JwtToken token = new JwtToken();
        token.setToken(jwt);
        token.setUser(user);
        token.setExpiration(jwtService.extractExpiration(jwt));
        token.setValid(true);

        jwtTokenRepository.save(token);
    }

    private AuthenticationResponse mapDTO(String jwt) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setJwt(jwt);

        return authenticationResponse;
    }

    public void logout(HttpServletRequest request) {
        String jwt = jwtService.extractJwtFromRequest(request);

        if(jwt == null || !StringUtils.hasText(jwt)) return;

        Optional<JwtToken> token = jwtTokenRepository.findByToken(jwt);
        if(token.isPresent() && token.get().isValid()){
            token.get().setValid(false);
            jwtTokenRepository.save(token.get());
        }
    }
}
