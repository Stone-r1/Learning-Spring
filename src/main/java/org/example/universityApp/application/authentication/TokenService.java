package org.example.universityApp.application.authentication;


import org.example.universityApp.domain.models.entities.User;


public interface TokenService {
    String generateToken(User user);

    boolean isTokenValid(String token, User user);

    String getUsernameFromToken(String token);
}
