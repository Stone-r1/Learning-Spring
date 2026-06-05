package org.example.universityApp.infrastructure.adapters;


import org.example.universityApp.domain.models.entities.Student;
import org.example.universityApp.domain.repositories.StudentRepository;
import org.example.universityApp.infrastructure.persistence.JpaStudentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class StudentRepositoryAdapter implements StudentRepository {
    private final JpaStudentRepository studentRepository;

    public StudentRepositoryAdapter(
            JpaStudentRepository studentRepository
    ) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<Student> findStudentByGovernmentId(
            String governmentId
    ) {
        return studentRepository.findStudentByGovernmentId(governmentId);
    }

    @Override
    public Student save(
            Student student
    ) {
        return studentRepository.save(student);
    }
}
