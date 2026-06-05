package org.example.universityApp.application.student;


import org.example.universityApp.domain.constants.StudentConstants;
import org.example.universityApp.domain.models.entities.Student;
import org.example.universityApp.domain.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StudentUseCase {
    private final StudentService studentService;

    public StudentUseCase(
            StudentService studentService
    ) {
        this.studentService = studentService;
    }

    public void createStudent(
            CreateStudentRequest request
    ) {
        Student student = studentService.getStudentByGovernmentId(request.governmentId());
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setGovernmentId(request.governmentId());
        student.setMobileNumber(request.mobileNumber());
        student.setFaculty(request.faculty());
        student.setAcademicYear(request.academicYear());
        student.setGpa(StudentConstants.DEFAULT_GPA);
        studentService.addStudent(request);
    }

    public Student getStudentByGovernmentId(
            String governmentId
    ) {
        return studentService.getStudentByGovernmentId(governmentId);
    }
}
