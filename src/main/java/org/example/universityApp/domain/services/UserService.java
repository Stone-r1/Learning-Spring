package org.example.universityApp.domain.services;


import org.example.universityApp.domain.exceptions.AuthenticationExceptions;
import org.example.universityApp.domain.models.entities.User;
import org.example.universityApp.domain.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isUsernameTaken(
            String username
    ) {
        return userRepository.findByUsername(username).isPresent();
    }

    public String registerUser(
            User user
    ) {
        if (isUsernameTaken(user.getUsername())) {
            throw new AuthenticationExceptions.UserAlreadyExistsException(
                    "Username " + user.getUsername() + " is already taken"
            );
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);
        return "User registered successfully";
    }

    public User getUserByUsername(
            String username
    ) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AuthenticationExceptions.UserNotFoundException(
                        "User with username " + username + " not found"
                )
        );
    }

    public User loginUser(
            User user
    ) {
        User foundUser = getUserByUsername(user.getUsername());

        if (!passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new AuthenticationExceptions.InvalidCredentialsException(
                    "Invalid credentials provided"
            );
        }

        return foundUser;
    }
}
