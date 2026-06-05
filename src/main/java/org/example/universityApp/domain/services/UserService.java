package org.example.universityApp.domain.services;


import org.example.universityApp.domain.repositories.UserRepository;



public class UserService {
    private final UserRepository userRepository;

    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }
}
