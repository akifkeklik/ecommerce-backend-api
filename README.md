
E-Commerce RESTful Backend API

Production-ready bir e-commerce backend API.
Spring Boot 3 ile geliştirilmiş, REST prensiplerine uygun, ölçeklenebilir ve gerçek projelerde kullanılabilecek şekilde tasarlanmıştır.

Overview
Bu proje frontend’den (Web / Mobile) bağımsız çalışan bir RESTful API sunar.
Tüm iş kuralları backend’de yer alır ve sistem JWT ile stateless olarak çalışır.

- Resource-based REST endpoints
- JSON request / response
- HTTP status code standardları
- API versioning (/api/v1)

Core Features
- Authentication & Authorization: JWT tabanlı login / register, role-based access control
- Product Management: Kategori, ürün, stok ve arama işlemleri
- Shopping Cart: Sepete ekleme / çıkarma, miktar güncelleme
- Order Management: Sipariş oluşturma, listeleme, iptal ve durum takibi
- Payments: Stripe / PayPal entegrasyonuna uygun altyapı

Architecture
Clean Architecture + DDD yaklaşımıyla tasarlanmıştır.

com.ecommerce
- api            → REST controllers
- application    → DTO, mapper, use-case servisleri
- domain         → Core business logic
- infrastructure → Database, security, external integrations

Tech Stack
Java 17
Spring Boot 3.x
Spring Security + JWT
Spring Data JPA
PostgreSQL
Redis
Flyway
OpenAPI / Swagger
Docker

API Example
POST /api/v1/auth/login
GET  /api/v1/products
POST /api/v1/cart/items
POST /api/v1/orders

Running Locally
docker-compose up -d postgres redis
mvn spring-boot:run

Swagger UI
http://localhost:8080/swagger-ui.html

Security
- Stateless JWT authentication
- BCrypt password hashing
- Role-based access
- Input validation

Notes
Bu proje backend API design ve RESTful servis mimarisi üzerine odaklanarak geliştirilmiştir.
Frontend ve mobile uygulamalar için doğrudan kullanılabilir.
