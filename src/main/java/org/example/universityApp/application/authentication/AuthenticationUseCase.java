package org.example.universityApp.application.authentication;


import org.example.universityApp.domain.models.entities.User;
import org.example.universityApp.domain.models.shared.Role;
import org.example.universityApp.domain.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthenticationUseCase {
    private final UserService userService;
    private final TokenService tokenService;

    public AuthenticationUseCase(
            UserService userService,
            TokenService tokenService
    ) {
        this.userService = userService;
        this.tokenService = tokenService;
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

    // Role is manually managed and injected in DB for professors and admins.
    // This is a test application, so I'm not implementing role-based registration for professors and admins.
    @Transactional
    public String registerUser(
            RegisterUserRequest request
    ) {
        return userService.registerUser(
                buildUser(
                        request.username(),
                        request.password(),
                        Role.STUDENT
                )
        );
    }

    @Transactional(readOnly = true)
    public String loginUser(
            LoginUserRequest request
    ) {
        User user = buildUser(
                request.username(),
                request.password(),
                Role.STUDENT
        );

        User authenticatedUser = userService.loginUser(user);
        return tokenService.generateToken(authenticatedUser);
    }
}
