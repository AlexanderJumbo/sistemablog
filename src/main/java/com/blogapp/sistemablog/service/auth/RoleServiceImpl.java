package com.blogapp.sistemablog.service.auth;

import com.blogapp.sistemablog.entity.security.Role;
import com.blogapp.sistemablog.repository.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findDefaultRole() {
        return roleRepository.findByName("BLOGGER");
    }
}
