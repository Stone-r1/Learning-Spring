package org.example.universityApp.domain.repositories;


import org.example.universityApp.domain.models.entities.Student;

import java.util.Optional;


public interface StudentRepository {
    public Optional<Student> findStudentByGovernmentId(String governmentId);
    Student save(Student student);
}
