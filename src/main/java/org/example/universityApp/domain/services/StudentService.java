package org.example.universityApp.domain.services;

import org.example.universityApp.domain.models.entities.Student;
import org.example.universityApp.domain.exceptions.UniversityExceptions;
import org.example.universityApp.domain.repositories.StudentRepository;


public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(
            StudentRepository studentRepository
    ) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(
            Student createStudentRequest
    ) {
        if (studentRepository.findStudentByGovernmentId(createStudentRequest.getGovernmentId()).isPresent()) {
            throw new UniversityExceptions.StudentAlreadyExistsException(
                    "Student with government id " + createStudentRequest.getGovernmentId() + " already exists"
            );
        }

        studentRepository.save(createStudentRequest);
    }

    public Student getStudentByGovernmentId(
            String governmentId
    ) {
        return studentRepository.findStudentByGovernmentId(governmentId)
                        .orElseThrow(() -> new UniversityExceptions.StudentNotFoundException(
                                "Could not fetch student as student is not registered in database"
                        )
        );
    }
}
