package com.blogapp.sistemablog.service.auth;

import com.blogapp.sistemablog.dto.Register.RegisterRequest;
import com.blogapp.sistemablog.dto.Register.RegisterResponse;
import com.blogapp.sistemablog.dto.User.UserResponse;
import com.blogapp.sistemablog.entity.security.Role;
import com.blogapp.sistemablog.entity.security.User;
import com.blogapp.sistemablog.exception.ObjectNotFoundException;
import com.blogapp.sistemablog.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;


    @Override
    public Optional<User> findByUsername(String author) {
        return userRepository.findByUsername(author);
    }

    @Override
    public UserResponse getInfoUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found. User Id: " + userId));

        return mapDTO(user);
    }

    @Override
    public User registerBlogger(RegisterRequest registerRequest) {

        /*RegisterResponse registerResponse = new RegisterResponse();
        User userSaved = userRepository.save(mapRegisterDTO(registerRequest));
        registerResponse.setJwt(userSaved.getName());
        return registerResponse;*/

        User newUser = new User();
        newUser.setName(registerRequest.getName());
        newUser.setUsername(registerRequest.getUserName());
        newUser.setDni(registerRequest.getDni());
        newUser.setAddress(registerRequest.getAddress());
        newUser.setNumberPhone(registerRequest.getNumberPhone());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        Role role = roleService.findDefaultRole()
                .orElseThrow(() -> new ObjectNotFoundException("Role not found."));
        newUser.setRole(role);

        return userRepository.save(newUser);
    }

    private static User mapRegisterDTO(RegisterRequest registerRequest) {
        User newUser = new User();
        newUser.setName(registerRequest.getName());
        newUser.setUsername(registerRequest.getUserName());
        newUser.setDni(registerRequest.getDni());
        newUser.setAddress(registerRequest.getAddress());
        newUser.setNumberPhone(registerRequest.getNumberPhone());
        newUser.setPassword(registerRequest.getPassword());

        return newUser;
    }

    private UserResponse mapDTO(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setUserName(user.getUsername());
        userResponse.setDni(user.getDni());
        userResponse.setAddress(user.getAddress());
        userResponse.setNumberPhone(user.getNumberPhone());
        userResponse.setFollowers(user.getFollowers().stream().map(follower -> follower.getFollower().getUsername()).collect(Collectors.toSet()));

        return userResponse;
    }
}
