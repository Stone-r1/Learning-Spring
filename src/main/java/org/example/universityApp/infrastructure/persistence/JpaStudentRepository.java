package org.example.universityApp.infrastructure.persistence;

import org.example.universityApp.application.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaStudentRepository extends JpaRepository<Student, Integer> {
    public Student findStudentByGovernmentId(Long governmentId);
}
