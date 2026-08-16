# Online Library Management System

---
# group_members
STUDENT - 01 R.S. AThapaththu ITBIN-2211-0143
| STUDENT - 02 M.M.S. Nethmini ITBIN-2211-0245
| STUDENT - 03 M.D.M.D. Kumari ITBIN-2211-0214


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

The Book Service is a Spring Boot microservice responsible for managing the library's book catalog. It exposes CRUD operations for books, supports searching by title or author, and filtering by category, while tracking each book's availability status.

### Swagger UI
- URL: http://localhost:8082/swagger-ui.html

### API Key Header Format
X-API-KEY: book-service-secret-key-2026

### Book Service Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|--------------|----------------|
| GET | /books | Get all books | Yes (API Key) |
| GET | /books/{id} | Get a book by ID | Yes (API Key) |
| GET | /books/search?keyword= | Search books by title or author | Yes (API Key) |
| GET | /books/category/{category} | Filter books by category | Yes (API Key) |
| POST | /books | Add a new book | Yes (API Key) |
| PUT | /books/{id} | Update a book | Yes (API Key) |
| DELETE | /books/{id} | Delete a book | Yes (API Key) |

### Docker Containerization (Docker Lead Responsibility)

As the Docker/Containerization Lead for the group, I designed and implemented the multi-stage Dockerfile pattern used across all microservices (Auth Service, Book Service, Borrowing Service):

- **Stage 1** — Maven + JDK 17 image compiles the source and produces an executable JAR (`mvn clean package -DskipTests`)
- **Stage 2** — copies only the resulting JAR into a lightweight JRE 17 Alpine image, keeping the final image small

I also authored the root `docker-compose.yml`, which orchestrates all four components (Auth Service, Book Service, Borrowing Service, and the API Gateway) so the entire system starts with a single command:

```bash
docker compose up --build
```

Each service's port is externalized through a `SERVER_PORT` environment variable, so container ports can be configured without rebuilding the image. The Book Service can also be built and run independently:

```bash
cd library-system/book-service/book-service
docker build -t book-service .
docker run -p 8082:8082 --name book-container book-service
```
## Student 3 — Borrowing Service

**Role:** Microservice Developer + Client App Lead
Handles borrowing and returning books. Secured with API Key, documented via Swagger, containerized with Docker.

| Method | Endpoint | Description |
|---|---|---|
| POST | `/borrowings` | Borrow a book |
| GET | `/borrowings` | View all borrowings |
| GET | `/borrowings/user/{userId}` | View a user's borrowing history |
| PUT | `/borrowings/{id}/return?returnDate=YYYY-MM-DD` | Return a book |

**API Key:** `X-API-KEY: library-secret-key-123`

**Run (Docker):**
```bash
cd borrowing-service/borrowing-service
docker build -t borrowing-service .
docker run -p 8083:8083 --name borrowing-container borrowing-service
```

**Swagger UI:** `http://localhost:8083/swagger-ui.html`
**Client App:** open `client-app/index.html` in a browser after starting the service.
```


