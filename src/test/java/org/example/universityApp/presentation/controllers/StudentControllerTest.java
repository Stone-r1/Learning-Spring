package org.example.universityApp.presentation.controllers;


import org.example.universityApp.application.student.CreateStudentRequest;
import org.example.universityApp.application.student.StudentUseCase;
import org.example.universityApp.domain.exceptions.UniversityExceptions;
import org.example.universityApp.domain.models.entities.Student;
import org.example.universityApp.domain.repositories.UserRepository;
import org.example.universityApp.infrastructure.security.JwtAuthFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.validation.autoconfigure.ValidationAutoConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(ValidationAutoConfiguration.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StudentUseCase studentUseCase;

    @MockitoBean
    private JwtAuthFilter jwtAuthFilter;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @WithMockUser(roles = "ADMIN")
    void addStudent_withValidRequest_returns201() throws Exception {
        CreateStudentRequest request = new CreateStudentRequest(
                "12345678901", "Stoney", "Dev",
                "Engineering", "555-0100", (short) 2
        );

        doNothing().when(studentUseCase).createStudent(any());

        mockMvc.perform(post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Student added successfully"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addStudent_withBlankFirstName_returns400() throws Exception {
        CreateStudentRequest request = new CreateStudentRequest(
                "12345678901", "", "Dev",
                "Engineering", "555-0100", (short) 2
        );

        mockMvc.perform(post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(studentUseCase, never()).createStudent(any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addStudent_withGovernmentIdWrongLength_returns400() throws Exception {
        CreateStudentRequest request = new CreateStudentRequest(
                "123", "Stoney", "Dev",        // governmentId too short
                "Engineering", "555-0100", (short) 2
        );

        mockMvc.perform(post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void addStudent_withDuplicateGovernmentId_returns409() throws Exception {
        CreateStudentRequest request = new CreateStudentRequest(
                "12345678901", "Stoney", "Dev",
                "Engineering", "555-0100", (short) 2
        );

        doThrow(new UniversityExceptions.StudentAlreadyExistsException("Already exists"))
                .when(studentUseCase).createStudent(any());

        mockMvc.perform(post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getStudent_withExistingGovernmentId_returns200() throws Exception {
        Student student = new Student();
        student.setGovernmentId("12345678901");
        student.setFirstName("Stoney");

        when(studentUseCase.getStudentByGovernmentId("12345678901"))
                .thenReturn(student);

        mockMvc.perform(get("/students/get/12345678901"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.governmentId").value("12345678901"))
                .andExpect(jsonPath("$.firstName").value("Stoney"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void getStudent_withMissingGovernmentId_returns404() throws Exception {
        when(studentUseCase.getStudentByGovernmentId("00000000000"))
                .thenThrow(new UniversityExceptions.StudentNotFoundException("Not found"));

        mockMvc.perform(get("/students/get/00000000000"))
                .andExpect(status().isNotFound());
    }
}