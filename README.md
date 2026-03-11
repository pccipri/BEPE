# Pharma Ease Backend (BEPE)

Pharma Ease Backend is a Java Spring Boot REST API. It provides authentication, user management, product and category management, order processing, and message handling for the frontend application.

## Overview

This project is the backend service for the Pharma Ease application. It exposes REST endpoints for both customer-facing and admin-facing operations and uses JWT-based authentication with Spring Security.

The API supports:

- User registration and login
- JWT token verification
- User management
- Product management
- Category management
- Order creation and order-product linking
- Message/contact handling

## Tech Stack

- Java 17
- Spring Boot 3.2
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (jjwt)
- Gradle
- MariaDB / MySQL-compatible database setup
- Lombok

## Project Structure

- `src/main/java/com/example/pharmaease/config` — security, password, and seed configuration
- `src/main/java/com/example/pharmaease/controllers` — REST API controllers
- `src/main/java/com/example/pharmaease/dto` — authentication request/response DTOs
- `src/main/java/com/example/pharmaease/filters` — JWT authentication filter
- `src/main/java/com/example/pharmaease/models` — JPA entities
- `src/main/java/com/example/pharmaease/repositories` — Spring Data repositories
- `src/main/java/com/example/pharmaease/services` — business logic and auth/JWT services
- `src/main/java/com/example/pharmaease/DataLoader.java`
- `src/main/java/com/example/pharmaease/DemoApplication.java`
- `src/main/resources/application.properties`
- `src/main/resources/application.properties.example`

## Features

### Authentication

- Register new users
- Login existing users
- Verify JWT tokens
- Role-based protected endpoints for `USER` and `ADMIN`

### Core API Modules

- **Users** — CRUD operations for user accounts
- **Products** — CRUD operations for products
- **Categories** — CRUD operations for categories
- **Orders** — create orders and associate products with quantities
- **Ordered Products** — manage order-product relationship records
- **Messages** — manage customer/user messages

## API Routes

Base path: `/api/v1`

### Authentication

- `POST /api/v1/register`
- `POST /api/v1/login`
- `POST /api/v1/verify-token`
- `GET /api/v1/onlyusers`
- `GET /api/v1/admins`

### Users

- `GET /api/v1/users`
- `GET /api/v1/users/{id}`
- `POST /api/v1/users`
- `PUT /api/v1/users/{id}`
- `DELETE /api/v1/users/{id}`

### Products

- `GET /api/v1/products`
- `GET /api/v1/products/{id}`
- `POST /api/v1/products`
- `PUT /api/v1/products/{id}`
- `DELETE /api/v1/products/{id}`

### Categories

- `GET /api/v1/categories`
- `GET /api/v1/categories/{id}`
- `POST /api/v1/categories`
- `PUT /api/v1/categories/{id}`
- `DELETE /api/v1/categories/{id}`

### Orders

- `GET /api/v1/orders`
- `GET /api/v1/orders/{id}`
- `GET /api/v1/orders/{orderId}/orderedProducts`
- `POST /api/v1/orders`
- `PUT /api/v1/orders/{id}`
- `DELETE /api/v1/orders/{id}`

### Ordered Products

- `GET /api/v1/orderedProduct`
- `GET /api/v1/orderedProduct/{id}`
- `POST /api/v1/orderedProduct`
- `PUT /api/v1/orderedProduct/{id}`
- `DELETE /api/v1/orderedProduct/{id}`

### Messages

- `GET /api/v1/messages`
- `GET /api/v1/messages/{id}`
- `POST /api/v1/messages`
- `PUT /api/v1/messages/{id}`
- `DELETE /api/v1/messages/{id}`

## Data Model

The backend includes entities for:

- User
- Product
- Category
- Order
- OrderedProduct
- Message
- Role

Examples of relationships:

- A Product is linked to a Category
- An Order is linked to a User
- OrderedProduct links an order to one or more products with quantities
- A Message is linked to a User

## Security

The application uses Spring Security with a stateless JWT authentication flow.

Key points:

- CSRF is disabled
- CORS is enabled
- Sessions are stateless
- JWT is processed through a custom `JwtAuthenticationFilter`
- CORS currently allows requests from `http://localhost:3000`

This matches the frontend development setup.

## Environment Configuration

The project uses the `dev` profile by default.

A template config file is included at:

`src/main/resources/application.properties.example`

To run the app locally, create:

`src/main/resources/application-dev.properties`

and add your environment-specific values.

Example values to configure:

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `spring.datasource.driver-class-name=org.mariadb.jdbc.Driver`
- `token.secret.key`
- `token.expirationms=3600000`

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/pccipri/BEPE.git
cd BEPE
```

### 2. Configure the application

Create:

`src/main/resources/application-dev.properties`

based on:

`src/main/resources/application.properties.example`

Fill in:

- database URL
- database username
- database password
- JWT secret key
- token expiration

### 3. Run the application

On macOS/Linux:

```bash
./gradlew bootRun
```

On Windows:

```bash
gradlew.bat bootRun
```

### 4. Default local URL

`http://localhost:8080`

## Seed Data

On startup, the application seeds a default admin user if the user table is empty.

Default seeded admin:

- **Email:** `admin@admin.com`
- **Password:** `123`
- **Role:** `ROLE_ADMIN`
