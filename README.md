# Store Application

## Overview
This is a Java Spring Boot application for managing a store, including user authentication, product management, and item handling. The project is structured to follow best practices for modularity, security, and scalability.

## Architecture

### 1. Layered Structure
The application follows a standard layered architecture:

- **Controller Layer**: Handles HTTP requests and responses. Controllers like `AuthController` and `ProductController` manage authentication and product-related endpoints.
- **Service Layer**: Contains business logic. The `ProductService` interface and its implementation in `ProductServiceImpl` encapsulate core operations.
- **Repository Layer**: Interfaces with the database using Spring Data JPA repositories such as `ProductRepository`, `ItemRepository`, and `AppUserRepository`.
- **DTOs (Data Transfer Objects)**: Classes like `ProductDto`, `ItemDto`, and `RegisterUserDto` are used to transfer data between layers, ensuring separation of concerns.
- **Entity Layer**: JPA entities like `Product`, `Item`, and `AppUser` represent database tables.
- **Security Layer**: Implements authentication and authorization using JWT. Classes like `JwtAuthenticationFilter`, `JwtUtil`, and `SecurityConfig` manage security concerns.
- **Configuration Layer**: Contains configuration classes such as `OpenApiConfig` for API documentation and `AuditorAwareImpl` for auditing.
- **Exception Handling**: Centralized error handling is provided by `GlobalExceptionHandler`.

### 2. Security
- **JWT Authentication**: The application uses JWT for stateless authentication. The `CustomUserDetailsService` loads user-specific data, and `JwtAuthenticationFilter` validates tokens for protected endpoints.
- **Spring Security**: Configured in `SecurityConfig` to define access rules and integrate JWT authentication.

### 3. API Documentation
- **OpenAPI/Swagger**: Configured via `OpenApiConfig` to provide interactive API documentation.

### 4. Database Access
- **Spring Data JPA**: Repositories abstract database operations, enabling easy CRUD functionality and query methods.

### 5. Exception Handling
- **GlobalExceptionHandler**: Handles exceptions across the application, providing consistent error responses.

## Project Structure
```
store/
├── src/
│   ├── main/
│   │   ├── java/com/zestindia/store/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── entity/
│   │   │   ├── dto/
│   │   │   ├── security/
│   │   │   ├── config/
│   │   │   └── exception/
│   │   └── resources/
│   └── test/
├── pom.xml
├── Dockerfile
├── docker-compose.yml
└── README.md
```

## Getting Started
1. **Build the project:**
   ```sh
   ./mvnw clean install
   ```
2. **Run the application:**
   ```sh
   ./mvnw spring-boot:run
   ```
3. **Access API docs:**
   Visit `http://localhost:8080/swagger-ui.html` after starting the app.

## Docker Support
- **Dockerfile** and **docker-compose.yml** are provided for containerized deployment.

## Contributing
Feel free to open issues or submit pull requests for improvements.

## License
This project is licensed under the MIT License.
