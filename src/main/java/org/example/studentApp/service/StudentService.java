package org.example.studentApp.service;


import org.example.studentApp.models.Student;
import org.example.studentApp.models.requests.CreateStudentRequest;
import org.example.studentApp.models.responses.StudentResponse;
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

    private StudentResponse toResponse(
            Student student
    ) {
        return new StudentResponse(
                student.getId(),
                student.getName(),
                student.getCourse()
        );
    }

    private List<StudentResponse> toResponse(
            List<Student> students
    ) {
        return students.stream()
                .map(this::toResponse)
                .toList();
    }

    public StudentResponse createStudent(
            CreateStudentRequest studentRequest
    ) {
        Student student = new Student();
        student.setName(studentRequest.name());
        student.setCourse(studentRequest.course());
        return toResponse(studentRepository.save(student));
    }

    public List<StudentResponse> getAllStudents() {
        return toResponse(studentRepository.findAll());
    }

    public StudentResponse getStudentById(
            Long id
    ) {
        return toResponse(findByIdOrThrow(id));
    }

    public StudentResponse updateStudentById(
            Long id,
            CreateStudentRequest studentRequest
    ) {
        Student existing = findByIdOrThrow(id);
        existing.setName(studentRequest.name());
        existing.setCourse(studentRequest.course());
        return toResponse(studentRepository.save(existing));
    }

    public StudentResponse deleteStudentById(
            Long id
    ) {
        Student student = findByIdOrThrow(id);
        studentRepository.delete(student);
        return toResponse(student);
    }
}
