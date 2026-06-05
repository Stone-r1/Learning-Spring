package org.example.universityApp.infrastructure.config;


import org.example.universityApp.domain.repositories.UserRepository;
import org.example.universityApp.domain.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class UserConfiguration {

    @Bean
    public UserService userService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder

    ) {
        return new UserService(userRepository, passwordEncoder);
    }
}
