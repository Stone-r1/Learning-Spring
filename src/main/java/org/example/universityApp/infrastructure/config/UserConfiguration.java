package org.example.universityApp.infrastructure.config;


import org.example.universityApp.domain.repositories.UserRepository;
import org.example.universityApp.domain.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfiguration {

    @Bean
    public UserService userService(
            UserRepository userRepository
    ) {
        return new UserService(userRepository);
    }
}
