package org.example.universityApp.application.student;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


public record CreateStudentRequest(

        @NotBlank
        @Length(min = 11, max = 11)
        String governmentId,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String faculty,

        @NotBlank
        String mobileNumber,

        @Min(1) @Max(4)
        Short academicYear
) {}
