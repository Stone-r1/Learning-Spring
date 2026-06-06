package org.example.universityApp.domain.services;


import org.example.universityApp.domain.exceptions.UniversityExceptions;
import org.example.universityApp.domain.models.entities.Student;
import org.example.universityApp.domain.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.universityApp.constants.MockData.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setGovernmentId(GOVERNMENT_ID);
        student.setFirstName(FIRST_NAME);
        student.setLastName("Dev");
        student.setFaculty("Engineering");
        student.setAcademicYear((short) 2);
        student.setMobileNumber("591-15-15-15");
    }

    @Test
    public void addStudent_withGovernmentId_saveStudent() {
        when(
                studentRepository.findStudentByGovernmentId(GOVERNMENT_ID)
        ).thenReturn(Optional.empty());

        studentService.addStudent(student);

        verify(studentRepository).save(student);
    }

    @Test
    public void addStudent_withDuplicateGovernmentId_throwsException() {
        when(
                studentRepository.findStudentByGovernmentId(GOVERNMENT_ID)
        ).thenReturn(Optional.of(student));

        assertThatThrownBy(() ->
                studentService.addStudent(student)
        ).isInstanceOf(UniversityExceptions.StudentAlreadyExistsException.class);

        verify(studentRepository, never()).save(any());
    }

    @Test
    public void getStudent_withExistingGovernmentId_returnsStudent() {
        when(
                studentRepository.findStudentByGovernmentId(GOVERNMENT_ID)
        ).thenReturn(Optional.of(student));

        Student fetchedStudent = studentService.getStudentByGovernmentId(GOVERNMENT_ID);

        assertThat(fetchedStudent.getGovernmentId()).isEqualTo(GOVERNMENT_ID);
        assertThat(fetchedStudent.getFirstName()).isEqualTo(FIRST_NAME);
    }

    @Test
    public void getStudent_withMissingGovernmentId_throwsException() {
        when(
                studentRepository.findStudentByGovernmentId(WRONG_GOVERNMENT_ID)
        ).thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                studentService.getStudentByGovernmentId(WRONG_GOVERNMENT_ID)
        ).isInstanceOf(UniversityExceptions.StudentNotFoundException.class);
    }
}
