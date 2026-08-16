# Online Library Management System
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

---