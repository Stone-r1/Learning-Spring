package org.example.universityApp.application.student;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;


@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    @Length(min = 11, max = 11)
    private String governmentId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Min(1) @Max(4)
    private Short academicYear;

    @NotBlank
    private String faculty;

    @NotBlank
    @NumberFormat
    private String mobileNumber;

    @Min(0) @Max(4)
    private Double Gpa;
}
