package org.example.studentApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;


@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class
        }
)
public class StudentApp {
    public static void main(
            String[] args
    ) {
        SpringApplication.run(StudentApp.class, args);
    }
}
