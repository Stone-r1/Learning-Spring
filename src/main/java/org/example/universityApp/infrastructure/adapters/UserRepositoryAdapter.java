package org.example.universityApp.infrastructure.adapters;


import org.example.universityApp.domain.models.entities.User;
import org.example.universityApp.domain.repositories.UserRepository;
import org.example.universityApp.infrastructure.persistence.JpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UserRepositoryAdapter implements UserRepository {
    private final JpaUserRepository userRepository;

    public UserRepositoryAdapter(
            JpaUserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
