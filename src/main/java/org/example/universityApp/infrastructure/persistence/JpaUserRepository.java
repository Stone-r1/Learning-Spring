package org.example.universityApp.infrastructure.persistence;


import org.example.universityApp.domain.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaUserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
