package com.blogapp.sistemablog.service.auth;

import com.blogapp.sistemablog.entity.security.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findDefaultRole();
}
