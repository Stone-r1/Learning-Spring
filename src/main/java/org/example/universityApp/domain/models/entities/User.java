package org.example.universityApp.domain.models.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.example.universityApp.domain.models.shared.Role;


@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
