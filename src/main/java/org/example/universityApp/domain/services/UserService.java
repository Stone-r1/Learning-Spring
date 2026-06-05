package org.example.universityApp.domain.services;


import org.example.universityApp.domain.exceptions.AuthenticationExceptions;
import org.example.universityApp.domain.models.entities.User;
import org.example.universityApp.domain.repositories.UserRepository;


public class UserService {
    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public boolean isUsernameTaken(
            String username
    ) {
        return userRepository.findByUsername(username).isPresent();
    }

    public void registerUser(
            User user
    ) {
        userRepository.save(user);
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

    public String loginUser(
            User user
    ) {
        User foundUser = getUserByUsername(user.getUsername());

        if (!foundUser.getPassword().equals(user.getPassword())) {
            throw new AuthenticationExceptions.InvalidCredentialsException(
                    "Invalid credentials provided"
            );
        }

        // temporary implementation, in a real application we would return a JWT token or similar
        return "Login successful";
    }
}
