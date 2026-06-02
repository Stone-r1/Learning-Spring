package org.example.studentApp.service;


import org.example.studentApp.models.Student;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentService {
    private final List<Student> students;

    public StudentService() {
        students = new ArrayList<>();

        System.out.println("Service Initialized");
    }

    public Student createStudent(
            Student student
    ) {
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(
            Long id
    ) {
        return students.stream()
                .filter(
                        student -> student.getId().equals(id)
                ).findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }

    public Student updateStudentById(
            Long id,
            Student student
    ) {
        return students.stream()
                .filter(
                        s -> s.getId().equals(id)
                ).findFirst()
                .map(s -> {
                    s.setName(student.getName());
                    s.setCourse(student.getCourse());
                    return s;
                }).orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
    }
}
