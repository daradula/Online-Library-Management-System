# Online Library Management System
# group_members
STUDENT - 01 R.S. AThapaththu
| STUDENT - 02 M.M.S. Nethmini
| STUDENT - 03 M.D.M.D. Kumari


## Auth Service (Student 1 - Rashmi)


### Swagger UI
- URL: http://localhost:8081/swagger-ui/index.html

### API Key Header Format (Standard for all microservices)
X-API-KEY: library-auth-service-secret-key-2026

### Auth Service Endpoints
| Method | Endpoint | Description | Auth Required |
|--------|----------|--------------|----------------|
| POST | /auth/register | Register new user | No |
| POST | /auth/login | Login user | No |
| GET | /auth/profile/{id} | Get user profile | Yes (API Key) |
| PUT | /auth/profile/{id} | Update user profile | Yes (API Key) |

### Test Credentials
- Email: kasun@example.com
- Password: password123

## API Gateway (Student: Rashmi)

### Gateway URL
http://localhost:8080

### OAuth Token Endpoint
POST http://localhost:8080/oauth/token
Body: {"email": "user@example.com"}

### Routes
- /auth/** → Auth Service (localhost:8081)

### Rate Limiting
20 requests per minute per client IP


## Student 2 — Book Service & Docker Lead

### Service Overview

The Book Service is a Spring Boot microservice responsible for managing the library's book catalog. It exposes CRUD operations for books, supports searching by title or author and filtering by category, and tracks each book's availability status.

### Docker Containerization (Docker Lead Responsibility)

As the Docker/Containerization Lead for the group, I designed and implemented the multi-stage Dockerfile pattern used across all three microservices (Auth Service, Book Service, Loan Service):

- **Stage 1** — Maven + JDK 17 image compiles the source and produces an executable JAR (`mvn clean package -DskipTests`)
- **Stage 2** — copies only the resulting JAR into a lightweight JRE 17 Alpine image, keeping the final image small

I also authored the root `docker-compose.yml`, which orchestrates all three microservices so the entire system starts with a single command:

```bash
docker compose up --build
```

Each service's port is externalized through a `SERVER_PORT` environment variable, so container ports can be configured without rebuilding the image. The Book Service can also be built and run independently:

```bash
docker build -t book-service .
docker run -p 8082:8082 --name book-container book-service
```
