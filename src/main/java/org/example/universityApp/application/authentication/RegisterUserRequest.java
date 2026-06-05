package org.example.universityApp.application.authentication;


public record RegisterUserRequest(
        String username,
        String password
) {}
