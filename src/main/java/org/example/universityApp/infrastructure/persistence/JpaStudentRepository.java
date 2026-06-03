package org.example.universityApp.infrastructure.persistence;

import org.example.universityApp.application.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JpaStudentRepository extends JpaRepository<Student, Long> {
    public Optional<Student> findStudentByGovernmentId(String governmentId);
}
