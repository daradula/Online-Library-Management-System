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


