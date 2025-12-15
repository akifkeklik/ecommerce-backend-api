# E-Commerce Backend RESTful API

A professional, production-ready **RESTful e-commerce backend API** built with Spring Boot 3.x, implementing **REST principles**, Clean Architecture, and Domain-Driven Design (DDD).

The system exposes versioned, stateless, resource-oriented HTTP endpoints that communicate via JSON and follow REST best practices.

## 🚀 Features

* **User Management (RESTful)**: Registration, authentication, profile management
* **Product Catalog (RESTful)**: Categories, products, images, inventory tracking
* **Shopping Cart (RESTful)**: Add/remove items, guest cart, merge on login
* **Order Management (RESTful)**: Order lifecycle, cancellation, status tracking
* **Payment Integration**: Stripe and PayPal support (external REST APIs)
* **Shipping**: Carrier REST API integration, tracking
* **Reviews & Ratings**: Product reviews with moderation
* **Promotions**: Discount codes, promotional campaigns

## 🌐 RESTful API Design Principles

This backend strictly follows RESTful design principles:

* **Resource-oriented URLs** (`/api/v1/products`, `/api/v1/orders/{id}`)
* **HTTP methods semantics** (GET, POST, PUT, DELETE)
* **Stateless authentication** using JWT
* **Versioned API** (`/api/v1`)
* **Consistent HTTP status codes**
* **JSON as the primary data format**
* **Separation of client and server** (frontend-independent)

## 🏗 Architecture

```
src/main/java/com/ecommerce/
├── api/                  # REST Controllers (HTTP layer)
│   ├── v1/               # REST API version 1
│   └── exception/        # Global REST exception handling
├── application/          # Application Layer
│   ├── dto/              # Request / Response DTOs
│   ├── mapper/           # MapStruct mappers
│   └── service/          # Application services (use cases)
├── domain/               # Domain Layer (DDD)
│   ├── user/             # User aggregate
│   ├── product/          # Product aggregate
│   ├── order/            # Order aggregate
│   ├── cart/             # Cart aggregate
│   ├── payment/          # Payment aggregate
│   ├── shipping/         # Shipping aggregate
│   ├── review/           # Review aggregate
│   ├── promotion/        # Promotion aggregate
│   └── exception/        # Domain exceptions
└── infrastructure/       # Infrastructure Layer
    ├── config/           # Configuration classes
    ├── security/         # Spring Security, JWT filters
    └── repository/       # JPA repositories
```

## 🛠 Tech Stack

* Java 17
* Spring Boot 3.2.x
* Spring Web (REST Controllers)
* Spring Security with JWT (stateless auth)
* Spring Data JPA with Hibernate
* PostgreSQL (primary database)
* Redis (caching & session-like data)
* Flyway (database migrations)
* MapStruct (DTO ↔ Entity mapping)
* SpringDoc OpenAPI (REST API documentation)
* JUnit 5 & Mockito (unit & integration testing)
* Docker & Docker Compose

## 📋 Prerequisites

* Java 17 or higher
* Maven 3.8+
* Docker & Docker Compose
* PostgreSQL 16 (or Docker)
* Redis 7 (or Docker)

## 🚀 Quick Start

### 1. Clone the repository

```bash
cd backend
```

### 2. Start infrastructure with Docker

```bash
docker-compose up -d postgres redis mailhog
```

### 3. Run the RESTful API

```bash
# With Maven
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Or with Java
java -jar target/ecommerce-backend-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev
```

### 4. Access REST resources

* **REST API Base URL**: [http://localhost:8080/api/v1](http://localhost:8080/api/v1)
* **Swagger UI (OpenAPI)**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* **Health Check**: [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
* **MailHog**: [http://localhost:8025](http://localhost:8025)
* **pgAdmin**: [http://localhost:5050](http://localhost:5050) (optional)

## 🔐 Authentication (REST + JWT)

The REST API uses **stateless JWT-based authentication**.

### Register a new user

```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "email": "john@example.com",
    "password": "securePassword123",
    "firstName": "John",
    "lastName": "Doe"
  }'
```

### Login

```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "usernameOrEmail": "johndoe",
    "password": "securePassword123"
  }'
```

### Access protected REST endpoints

```bash
curl http://localhost:8080/api/v1/cart \
  -H "Authorization: Bearer YOUR_ACCESS_TOKEN"
```

## 📚 REST API Endpoints

### Authentication

| Method | Endpoint              | Description       |
| ------ | --------------------- | ----------------- |
| POST   | /api/v1/auth/register | Register new user |
| POST   | /api/v1/auth/login    | Login             |
| POST   | /api/v1/auth/refresh  | Refresh JWT token |
| POST   | /api/v1/auth/logout   | Logout            |

### Products

| Method | Endpoint                     | Description               |
| ------ | ---------------------------- | ------------------------- |
| GET    | /api/v1/products             | List products (paginated) |
| GET    | /api/v1/products/{id}        | Get product by ID         |
| GET    | /api/v1/products/slug/{slug} | Get product by slug       |
| GET    | /api/v1/products/search?q=   | Search products           |
| GET    | /api/v1/products/featured    | Get featured products     |
| POST   | /api/v1/products             | Create product (SELLER)   |

### Cart

| Method | Endpoint                | Description          |
| ------ | ----------------------- | -------------------- |
| GET    | /api/v1/cart            | Get current cart     |
| POST   | /api/v1/cart/items      | Add item to cart     |
| PUT    | /api/v1/cart/items/{id} | Update item quantity |
| DELETE | /api/v1/cart/items/{id} | Remove item          |
| DELETE | /api/v1/cart            | Clear cart           |

### Orders

| Method | Endpoint                   | Description     |
| ------ | -------------------------- | --------------- |
| POST   | /api/v1/orders             | Create order    |
| GET    | /api/v1/orders             | Get user orders |
| GET    | /api/v1/orders/{id}        | Get order by ID |
| POST   | /api/v1/orders/{id}/cancel | Cancel order    |

## ⚙️ Configuration

### Environment Variables

| Variable         | Description           | Default    |
| ---------------- | --------------------- | ---------- |
| DB_HOST          | PostgreSQL host       | localhost  |
| DB_PORT          | PostgreSQL port       | 5432       |
| DB_NAME          | Database name         | ecommerce  |
| DB_USERNAME      | Database user         | postgres   |
| DB_PASSWORD      | Database password     | postgres   |
| REDIS_HOST       | Redis host            | localhost  |
| REDIS_PORT       | Redis port            | 6379       |
| JWT_SECRET       | JWT signing secret    | (required) |
| STRIPE_API_KEY   | Stripe REST API key   | -          |
| PAYPAL_CLIENT_ID | PayPal REST client ID | -          |

## 🧪 Testing

```bash
# Run all REST API tests
mvn test

# Run with coverage report
mvn verify

# Run REST integration tests only
mvn test -Dtest=*IntegrationTest
```

## 🐳 Docker

### Build image

```bash
docker build -t ecommerce-rest-api .
```

### Run with Docker Compose

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f app

# Stop all services
docker-compose down
```

## 📊 Monitoring

* Health: `/actuator/health`
* Info: `/actuator/info`
* Metrics: `/actuator/metrics`

## 🔒 Security Features

* Stateless REST authentication with JWT
* BCrypt password hashing (strength 12)
* Role-based access control (ADMIN, CUSTOMER, SELLER)
* Refresh token support
* Input validation (REST request validation)
* SQL injection prevention (JPA parameterized queries)
* CORS configuration for frontend clients

## 📁 Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/ecommerce/
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       ├── application-prod.yml
│   │       └── db/migration/     # Flyway SQL migrations
│   └── test/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## 📄 License

MIT License

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request
