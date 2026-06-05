package org.example.universityApp.application.authentication;

import org.example.universityApp.domain.models.shared.Role;


public record RegisterUserRequest(
        String username,
        String password,
        Role role
) {}
