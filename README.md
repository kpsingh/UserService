# 🛒 E-Commerce Backend (Microservices with Spring Boot)

## 📌 Overview
This project is a **microservices-based E-Commerce Backend** built with **Java, Spring Boot**, and deployed on **AWS**.  
It provides APIs for user management, product catalog, shopping cart, orders, payments, and notifications.  
The system follows **event-driven architecture** using Kafka and leverages Redis for caching and Elasticsearch for search.  

---

## 🚀 Microservices
The backend consists of the following independent services:

1. **User Service** – Handles user registration, login, authentication, and role management.  
2. **Product Service** – Manages product catalog and integrates with Elasticsearch for search.  
3. **Cart Service** – Manages shopping carts for customers.  
4. **Order Service** – Converts carts into orders and integrates with payment service.  
5. **Payment Service** – Processes order payments and simulates integration with external gateways.  
6. **Notification Service** – Sends email notifications (order/payment updates) using AWS SES.  

---

## 🛠 Tech Stack
- **Backend**: Java 17, Spring Boot  
- **Database**: MySQL (Amazon RDS)  
- **Cache**: Redis (Amazon ElastiCache)  
- **Event Streaming**: Apache Kafka (Amazon MSK)  
- **Search**: Elasticsearch (Amazon OpenSearch)  
- **API Gateway**: Kong  
- **Cloud**: AWS (EC2, Elastic Beanstalk, RDS, MSK, ElastiCache, SES, CloudWatch)  
- **Build & Deploy**: Maven, Docker  

---

## 📂 Service Details

### 1️⃣ User Service
- **Features**: User registration, login, JWT authentication, role management.  
- **APIs**:  
  - `POST /api/users/register` → Register user  
  - `POST /api/users/login` → Authenticate and get JWT  
  - `GET /api/users/{id}` → Get user details  
- **Port**: 8081  

---

### 2️⃣ Product Service
- **Features**: Product management, search, stock updates.  
- **APIs**:  
  - `POST /api/products` → Add product  
  - `GET /api/products` → Get all products  
  - `GET /api/products/search?q=keyword` → Search products  
- **Port**: 8082  

---

### 3️⃣ Cart Service
- **Features**: Manage user cart, add/remove/update items.  
- **APIs**:  
  - `POST /api/carts/{userId}` → Create cart  
  - `POST /api/carts/{userId}/items` → Add item to cart  
  - `GET /api/carts/{userId}` → View cart  
- **Port**: 8083  

---

### 4️⃣ Order Service
- **Features**: Place orders, view order history, update status.  
- **APIs**:  
  - `POST /api/orders/checkout` → Checkout & place order  
  - `GET /api/orders/{userId}` → User order history  
- **Port**: 8084  

---

### 5️⃣ Payment Service
- **Features**: Payment initiation, verification, Kafka event publishing.  
- **APIs**:  
  - `POST /api/payments` → Process payment  
  - `GET /api/payments/{id}` → Get payment details  
- **Port**: 8085  

---

### 6️⃣ Notification Service
- **Features**: Event-driven notifications via Kafka + AWS SES.  
- **APIs**:  
  - `GET /api/notifications/{userId}` → Fetch notifications history  
- **Port**: 8086  
