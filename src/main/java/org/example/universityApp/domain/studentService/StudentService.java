package org.example.universityApp.domain.studentService;


import org.example.universityApp.application.student.CreateStudentRequest;
import org.example.universityApp.application.student.Student;
import org.example.universityApp.infrastructure.persistence.JpaStudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.example.universityApp.domain.constants.StudentConstants.*;


@Service
public class StudentService {
    private final JpaStudentRepository studentRepository;

    public StudentService(
            JpaStudentRepository studentRepository
    ) {
        this.studentRepository = studentRepository;
    }

    private void updateStudent(
            CreateStudentRequest createStudentRequest,
            Student studentToUpdate
    ) {
        studentToUpdate.setFirstName(createStudentRequest.firstName());
        studentToUpdate.setLastName(createStudentRequest.lastName());
        studentToUpdate.setGovernmentId(createStudentRequest.governmentId());
        studentToUpdate.setMobileNumber(createStudentRequest.mobileNumber());
        studentToUpdate.setFaculty(createStudentRequest.faculty());
        studentToUpdate.setAcademicYear(createStudentRequest.academicYear());
        studentRepository.save(studentToUpdate);
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
            updateStudent(createStudentRequest, student.get());
        } else {
            createStudent(createStudentRequest);
        }
    }
}
