package org.example.universityApp.domain.services;


import org.example.universityApp.application.student.CreateStudentRequest;
import org.example.universityApp.application.student.GetStudentResponse;
import org.example.universityApp.domain.models.entities.Student;
import org.example.universityApp.domain.exceptions.UniversityExceptions;
import org.example.universityApp.domain.repositories.StudentRepository;

import java.util.Optional;

import static org.example.universityApp.domain.constants.StudentConstants.*;


public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(
            StudentRepository studentRepository
    ) {
        this.studentRepository = studentRepository;
    }

    private GetStudentResponse toStudentResponse(
            Student student
    ) {
        return new GetStudentResponse(
                student.getGovernmentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAcademicYear(),
                student.getFaculty(),
                student.getMobileNumber(),
                student.getGpa()
        );
    }

    private void createStudent(
            CreateStudentRequest createStudentRequest
    ) {
        Student student = new Student();
        student.setFirstName(createStudentRequest.firstName());
        student.setLastName(createStudentRequest.lastName());
        student.setGovernmentId(createStudentRequest.governmentId());
        student.setMobileNumber(createStudentRequest.mobileNumber());
        student.setFaculty(createStudentRequest.faculty());
        student.setAcademicYear(createStudentRequest.academicYear());
        student.setGpa(DEFAULT_GPA);
        studentRepository.save(student);
    }

    public void addStudent(
            CreateStudentRequest createStudentRequest
    ) {
        Optional<Student> student = studentRepository.findStudentByGovernmentId(createStudentRequest.governmentId());

        if (student.isPresent()) {
            throw new UniversityExceptions.StudentAlreadyExistsException(
                    "Student already present in the database. Either update the existing user with government id = "
                            + createStudentRequest.governmentId()
                            + " create new one with unique government id"
            );
        } else {
            createStudent(createStudentRequest);
        }
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
