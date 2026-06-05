package org.example.universityApp.application.authentication;


import org.example.universityApp.domain.models.entities.User;
import org.example.universityApp.domain.services.UserService;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationUseCase {
    private final UserService userService;

    public AuthenticationUseCase(
            UserService userService
    ) {
        this.userService = userService;
    }

    private User buildUser(
        String username,
        String password
    ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    public String registerUser(
            RegisterUserRequest request
    ) {
        return userService.registerUser(buildUser(request.username(), request.password()));
    }

    public String loginUser(
            LoginUserRequest request
    ) {
        return userService.loginUser(buildUser(request.username(), request.password()));
    }
}
