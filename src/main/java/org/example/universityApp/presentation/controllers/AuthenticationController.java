package org.example.universityApp.presentation.controllers;


import jakarta.validation.Valid;
import org.example.universityApp.application.authentication.AuthenticationUseCase;
import org.example.universityApp.application.authentication.LoginUserRequest;
import org.example.universityApp.application.authentication.RegisterUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationUseCase authenticationUseCase;

    public AuthenticationController(
            AuthenticationUseCase authenticationUseCase
    ) {
        this.authenticationUseCase = authenticationUseCase;
    }

    @PostMapping("/login")
    public String login(
            @Valid @RequestBody LoginUserRequest loginUserRequest
    ) {
        return authenticationUseCase.loginUser(loginUserRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterUserRequest registerUserRequest
    ) {
        String body = authenticationUseCase.registerUser(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}
