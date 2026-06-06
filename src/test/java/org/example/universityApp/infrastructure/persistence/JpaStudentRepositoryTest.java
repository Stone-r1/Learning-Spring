package org.example.universityApp.infrastructure.persistence;

import org.example.universityApp.domain.models.entities.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.universityApp.constants.MockData.*;


@DataJpaTest
class JpaStudentRepositoryTest {

    @Autowired
    private JpaStudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setGovernmentId(GOVERNMENT_ID);
        student.setFirstName(FIRST_NAME);
        student.setLastName(LAST_NAME);
        student.setFaculty(FACULTY);
        student.setAcademicYear(ACADEMIC_YEAR);
        student.setMobileNumber(PHONE_NUMBER);
        student.setGpa(GPA);
        studentRepository.save(student);
    }

    @Test
    void findStudentByGovernmentId_withExistingId_returnsStudent() {
        Optional<Student> result = studentRepository.findStudentByGovernmentId(GOVERNMENT_ID);

        assertThat(result).isPresent();
        assertThat(result.get().getFirstName()).isEqualTo(FIRST_NAME);
    }

    @Test
    void findStudentByGovernmentId_withMissingId_returnsEmpty() {
        Optional<Student> result = studentRepository.findStudentByGovernmentId(WRONG_GOVERNMENT_ID);

        assertThat(result).isEmpty();
    }

    @Test
    void save_persistsAllFields() {
        Optional<Student> result = studentRepository.findStudentByGovernmentId(GOVERNMENT_ID);

        assertThat(result).isPresent();
        assertThat(result.get().getGovernmentId()).isEqualTo(GOVERNMENT_ID);
        assertThat(result.get().getFaculty()).isEqualTo(FACULTY);
        assertThat(result.get().getAcademicYear()).isEqualTo(ACADEMIC_YEAR);
    }
}