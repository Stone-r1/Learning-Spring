package org.example.universityApp.application.authentication;


import org.example.universityApp.domain.models.entities.User;
import org.example.universityApp.domain.models.shared.Role;
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
        String password,
        Role role
    ) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        return user;
    }

    public String registerUser(
            RegisterUserRequest request
    ) {
        return userService.registerUser(
                buildUser(
                        request.username(),
                        request.password(),
                        request.role()
                )
        );
    }

    public String loginUser(
            LoginUserRequest request
    ) {
        return userService.loginUser(
                buildUser(
                        request.username(),
                        request.password(),
                        Role.USER // role is not being checked during login, so we can set it to a default value
                )
        );
    }
}
