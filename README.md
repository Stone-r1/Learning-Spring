# University Management API

A Spring Boot REST API for managing students, courses, and enrollments.
Role-based system is not fully implemented as the project's sole purpose was learning spring.

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Security (JWT)
- Spring Data JPA
- PostgreSQL
- Lombok
- JUnit 5, Mockito, AssertJ

## Architecture
Hexagonal Architecture:
presentation → application → domain  
infrastructure → domain

## Layers
- presentation: REST controllers
- application: use cases (business logic)
- domain: core logic, entities, repository interfaces
- infrastructure: DB, security, Spring config

## Project Structure

```
src/main/java/org/example/universityApp/
├── application/
│   ├── authentication/     AuthenticationUseCase, TokenService, request records
│   ├── course/             CourseUseCase, request/response records
│   ├── enrollment/         EnrollmentUseCase, request records
│   └── student/            StudentUseCase, request/response records
├── domain/
│   ├── constants/          StudentConstants (default GPA)
│   ├── exceptions/         DomainException base, UniversityExceptions, AuthenticationExceptions
│   ├── models/
│   │   ├── entities/       Student, Course, Enrollment, User
│   │   └── shared/         Role enum
│   ├── repositories/       Port interfaces — StudentRepository, CourseRepository, etc.
│   └── services/           StudentService, CourseService, EnrollmentService, UserService
├── infrastructure/
│   ├── adapters/           JPA adapter implementations of domain repository interfaces
│   ├── config/             @Configuration classes that wire domain services
│   ├── persistence/        Spring Data JPA repository interfaces
│   └── security/           JwtAuthFilter, JwtService, SecurityConfig, UserConfig
└── presentation/
    ├── controllers/        StudentController, CourseController, EnrollmentController, AuthenticationController
    └── response/
        ├── exceptionHandlers/  UniversityExceptionsHandler (@RestControllerAdvice)
        └── models/             ErrorMessage
```

## Setup

### Database
CREATE DATABASE universitydb;

### Environment variables

```properties
DB_URL=jdbc:postgresql://localhost:5432/universitydb
DB_USERNAME=your_username
DB_PASSWORD=your_password
JWT_SECRET=your_secret_key_at_least_32_characters_long
JWT_EXPIRATION=3600000
```

### Run
```bash
mvn spring-boot:run
```

App runs on:
http://localhost:8080

## Authentication
JWT-based auth.

Header:
Authorization: Bearer <token>

## Roles
- STUDENT (auto)
- ADMIN (DB)
- PROFESSOR (DB)

## API

### Auth
POST /auth/register
POST /auth/login

### Students
POST /students/add
GET /students/get/{governmentId}

### Courses
POST /courses/add
GET /courses/get

### Enrollments
POST /enrollments/create (ADMIN only)

## Error Handling
- 400 validation error
- 401 unauthorized
- 403 forbidden
- 404 not found
- 409 conflict

## Testing
```bash
mvn test
```

Types:
- Unit (services)
- Web (controllers)
- JPA (repositories)
- Integration (full flow)

## Summary
Clean layered architecture with strict separation between domain and infrastructure.
