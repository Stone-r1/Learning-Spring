package org.example.studentApp.service;


import org.example.studentApp.models.Student;
import org.example.studentApp.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(
            StudentRepository studentRepository
    ) {
        this.studentRepository = studentRepository;

        System.out.println("Service Initialized");
    }

    private Student findByIdOrThrow(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Student createStudent(
            Student student
    ) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(
            Long id
    ) {
        return findByIdOrThrow(id);
    }

    public Student updateStudentById(
            Long id,
            Student student
    ) {
        Student existing = findByIdOrThrow(id);
        existing.setName(student.getName());
        existing.setCourse(student.getCourse());
        return studentRepository.save(existing);
    }

    public Student deleteStudentById(
            Long id
    ) {
        Student student = findByIdOrThrow(id);
        studentRepository.delete(student);
        return student;
    }
}
