package org.example.studentApp.repository;

import org.example.studentApp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {}