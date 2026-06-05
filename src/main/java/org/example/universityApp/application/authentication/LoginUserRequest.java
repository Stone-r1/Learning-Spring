package org.example.universityApp.application.authentication;

public record LoginUserRequest(
        String username,
        String password
) {}