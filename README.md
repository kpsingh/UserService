# User Service

## Overview
The User Service manages customer and admin accounts, authentication, and authorization.  
It handles **registration, login, JWT token generation, and role-based access control**.

## Features
- User registration with email/phone
- Secure login with JWT authentication
- Role management: CUSTOMER, ADMIN
- Password hashing with BCrypt

## Tech Stack
- Java 17, Spring Boot
- Spring Security, JWT
- Spring Data JPA + MySQL (RDS)
- Dockerized microservice

## API Endpoints
- `POST /api/users/register` – Register a new user
- `POST /api/users/login` – Authenticate user and return JWT
- `GET /api/users/{id}` – Get user profile
- `DELETE /api/users/{id}` – Delete a user (Admin only)

## How to Run
### Locally
```bash
mvn spring-boot:run
