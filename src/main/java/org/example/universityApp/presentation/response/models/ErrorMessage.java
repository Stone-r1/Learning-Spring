package org.example.universityApp.presentation.response.models;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class ErrorMessage {
    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
