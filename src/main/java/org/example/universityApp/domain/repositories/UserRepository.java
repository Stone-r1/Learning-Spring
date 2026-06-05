package org.example.universityApp.domain.repositories;


import org.example.universityApp.domain.models.entities.User;

import java.util.Optional;


public interface UserRepository {
    Optional<User> findByUsername(String username);
    User save(User user);
}
