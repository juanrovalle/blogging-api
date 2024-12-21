# Blogging API Spring Boot

## Description

This project is a simple blogging platform built with Spring Boot. It allows you to manage blog posts and comments
through a RESTful API.

## Prerequisites

Before running the application, ensure you have the following installed:

- Java 21 or higher
- Maven
- Docker (optional, for containerized deployment)

## Running the Application

### Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/juanrovalle/blogging-api.git
   cd blogging-api
   ```
2. Build the project with Maven:
   ```bash
   mvn clean package
   ```

3. Run the application:
   ```bash
   java -jar target/blogging-api-0.0.1-SNAPSHOT.jar
   ```

4. Access the API at:
   ```
   http://localhost:8080/api/posts
   ```

### Using Docker

1. Build the Docker image:
   ```bash
   docker build -t blogging-api .
   ```

2. Run the Docker container:
   ```bash
   docker run -p 8080:8080 blogging-api
   ```

3. Access the API at:
   ```
   http://localhost:8080/api/posts
   ```

## H2 Database Configuration

The application uses an in-memory H2 database for development. By default, it is configured as follows:

### Default Configuration in `application.properties`:

```properties
# H2 Database Configuration (Default for Local Development)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
# Enable H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Accessing the H2 Console

Once the application is running, you can access the H2 console at:

```
http://localhost:8080/h2-console
```

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **User**: `sa`
- **Password**: `password`

## API Endpoints

### Blog Posts

- **GET /api/posts**: Get all blog posts.
- **POST /api/posts**: Create a new blog post.
- **GET /api/posts/{id}**: Get a specific blog post by ID.
- **PUT /api/posts/{id}**: Update a specific blog post.
- **DELETE /api/posts/{id}**: Delete a specific blog post.

### Comments

- **POST /api/posts/{id}/comments**: Add a comment to a blog post.

## Next Steps

If more time were available, I would:

1. **Add Security**:
    - Implement authentication and authorization using Spring Security.

2. **Expand Features**:
    - Add pagination for retrieving posts and comments.
    - Implement search functionality for blog posts.

3. **Improve Testing**:
    - Add integration tests for API endpoints.
    - Achieve higher test coverage with additional unit tests.

4. **Deployment**:
    - Set up CI/CD pipelines for automated builds and deployments.
    - Deploy the application to a cloud platform like AWS or Azure.

5. **Frontend Integration**:
    - Create a React or Angular frontend for a complete blogging platform.

6. **Add MapStruct for DTOs and Validation**:
    - Use MapStruct to simplify entity-to-DTO mapping and vice versa.
    - Enhance validation using `spring-boot-starter-validation` to ensure data integrity and proper error handling.
7. **Add API Versioning**:
    - Implement API versioning to ensure backward compatibility for clients.
    - Use versioned routes like `/v1/api/posts` and `/v2/api/posts` for introducing breaking changes.

## Troubleshooting

If you encounter issues, ensure:

- The database is properly configured (H2 database in memory by default).
- Ports used by the application (8080) are not in use by another service.

Feel free to reach out for support or suggestions! 😊
