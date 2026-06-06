package org.example.universityApp.application.student;


import org.example.universityApp.domain.constants.StudentConstants;
import org.example.universityApp.domain.models.entities.Student;
import org.example.universityApp.domain.services.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentUseCase {
    private final StudentService studentService;

    public StudentUseCase(
            StudentService studentService
    ) {
        this.studentService = studentService;
    }

    @Transactional
    public void createStudent(
            CreateStudentRequest request
    ) {
        Student student = new Student();
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setGovernmentId(request.governmentId());
        student.setMobileNumber(request.mobileNumber());
        student.setFaculty(request.faculty());
        student.setAcademicYear(request.academicYear());
        student.setGpa(StudentConstants.DEFAULT_GPA);
        studentService.addStudent(student);
    }

    @Transactional(readOnly = true)
    public Student getStudentByGovernmentId(
            String governmentId
    ) {
        return studentService.getStudentByGovernmentId(governmentId);
    }
}
