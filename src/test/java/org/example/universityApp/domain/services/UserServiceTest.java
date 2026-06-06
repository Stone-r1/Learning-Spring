package org.example.universityApp.domain.services;


import org.example.universityApp.domain.exceptions.AuthenticationExceptions;
import org.example.universityApp.domain.models.entities.User;
import org.example.universityApp.domain.models.shared.Role;
import org.example.universityApp.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.universityApp.constants.MockData.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername(USERNAME);
        user.setPassword(PASSWORD1);
        user.setRole(Role.STUDENT);
    }

    @Test
    public void registerUser_withNewUsername_encodesPasswordAndSaves() {
        when(
                userRepository.findByUsername(USERNAME)
        ).thenReturn(Optional.empty());

        when(
                passwordEncoder.encode(PASSWORD1)
        ).thenReturn(HASHED);

        userService.registerUser(user);

        verify(passwordEncoder).encode(PASSWORD1);
        verify(userRepository).save(user);
    }

    @Test
    public void registerUser_withTakenUsername_throwsException() {
        when(
                userRepository.findByUsername(USERNAME)
        ).thenReturn(Optional.of(user));

        assertThatThrownBy(() ->
                userService.registerUser(user)
        ).isInstanceOf(AuthenticationExceptions.UserAlreadyExistsException.class);

        verify(userRepository, never()).save(any());
    }

    @Test
    public void loginUser_withWrongPassword_throwsException() {
        User testUser = new User();
        testUser.setUsername(USERNAME);
        testUser.setPassword(HASHED);

        when(
                userRepository.findByUsername(USERNAME)
        ).thenReturn(Optional.of(user));

        when(
                passwordEncoder.matches(HASHED, PASSWORD1)
        ).thenReturn(false);

        assertThatThrownBy(() ->
                userService.loginUser(testUser)
        ).isInstanceOf(AuthenticationExceptions.InvalidCredentialsException.class);
    }

    @Test
    public void loginUser_withNonExistentUsername_throwsUserNotFoundException() {
        when(
                userRepository.findByUsername(USERNAME)
        ).thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                userService.getUserByUsername(USERNAME)
        ).isInstanceOf(AuthenticationExceptions.UserNotFoundException.class);
    }
}
