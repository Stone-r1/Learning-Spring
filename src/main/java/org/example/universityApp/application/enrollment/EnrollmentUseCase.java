package org.example.universityApp.application.enrollment;

import org.example.universityApp.domain.models.entities.Enrollment;
import org.example.universityApp.domain.services.CourseService;
import org.example.universityApp.domain.services.EnrollmentService;
import org.example.universityApp.domain.services.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EnrollmentUseCase {
    private final EnrollmentService enrollmentService;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentUseCase(
            EnrollmentService enrollmentService,
            StudentService studentService,
            CourseService courseService
    ) {
        this.enrollmentService = enrollmentService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Transactional
    public void createEnrollment(
            CreateEnrollmentRequest request
    ) {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(courseService.getCourseByCode(request.courseCode()));
        enrollment.setStudent(studentService.getStudentByGovernmentId(request.governmentId()));
        enrollmentService.createEnrollment(enrollment);
    }
}
