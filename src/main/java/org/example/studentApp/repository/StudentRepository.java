package org.example.studentApp.repository;

import org.example.studentApp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourse(Long course);
}