# 🎮 MyRoblox

MyRoblox is a fictional gaming platform built to study and demonstrate modern Java backend development using a microservices architecture.

The project is designed as a hands-on learning environment covering topics commonly required in backend Java interviews, including Spring Boot, Spring Cloud, Kafka, Docker, cloud services, and distributed systems.

> **Status:** In Development 🚧

---

# Technologies

- Java 21
- Spring Boot
- Spring Cloud
- Spring Data JPA
- Spring Cloud Gateway
- OpenFeign
- Resilience4j (Circuit Breaker)
- Apache Kafka
- PostgreSQL
- Oracle Database
- Docker
- Swagger / OpenAPI
- Maven

---

# Architecture

```
                        Client
                           │
                           ▼
                     API Gateway
                     (Port 8080)
                     /         \
                    /           \
                   ▼             ▼
          User Service      Game Service
           (Oracle)         (PostgreSQL)
                                 │
                                 │ OpenFeign
                                 ▼
                          User Service
                                 │
                                 │
                           Circuit Breaker
                                 │
                                 ▼
                          Apache Kafka
                                 │
                                 ▼
                     Notification Service
```

---

# Services

## User Service

Responsible for user management.

### Features

- Create user
- Update user
- Delete user
- Search user
- Search by username
- Swagger documentation
- Oracle Database

Default Port:

```
8082
```

---

## Game Service

Responsible for game management.

### Features

- Create game
- Update game
- Delete game
- Search game
- Search by name
- Validate creator user using OpenFeign
- Circuit Breaker with Resilience4j
- Publish Kafka events
- PostgreSQL

Default Port:

```
8083
```

---

## Notification Service

Responsible for consuming events.

### Features

- Kafka Consumer
- Receive GameCreatedEvent
- Simulate notifications

Default Port:

```
8085
```

---

## API Gateway

Single entry point for all APIs.

Default Port:

```
8080
```

Routes:

```
/user/**
/games/**
```

---

# Communication

## Synchronous

Game Service

↓

OpenFeign

↓

User Service

Features:

- User validation
- Circuit Breaker
- Error Decoder

---

## Asynchronous

Game Service

↓

Kafka Topic

↓

Notification Service

Current Event:

```
GameCreatedEvent
```

---

# Databases

## User Service

Database:

```
Oracle
```

Table:

```
TB_USER
```

---

## Game Service

Database:

```
PostgreSQL
```

Table:

```
TB_GAME
```

---

# Running the project

## 1. Start databases

Inside each service:

```
docker compose up -d
```

---

## 2. Start Kafka

```
docker compose up -d
```

---

## 3. Run services

Start:

```
User Service
Game Service
Notification Service
API Gateway
```

---

# API Documentation

Swagger:

User Service

```
http://localhost:8082/swagger-ui.html
```

Game Service

```
http://localhost:8083/swagger-ui.html
```

---

# Current Features

- REST APIs
- CRUD operations
- Swagger
- PostgreSQL
- Oracle
- Docker
- API Gateway
- OpenFeign
- Circuit Breaker
- Kafka Producer
- Kafka Consumer
- Event Driven Communication

---

# Roadmap

## Completed

- [x] User Service
- [x] Game Service
- [x] Notification Service
- [x] API Gateway
- [x] OpenFeign
- [x] Circuit Breaker
- [x] Kafka
- [x] Docker

## In Progress

- [ ] AWS S3
- [ ] AWS SQS
- [ ] AWS SNS
- [ ] AWS Lambda
- [ ] Authentication (JWT)
- [ ] BFF (Backend For Frontend)
- [ ] Docker Compose (entire platform)
- [ ] Monitoring (Prometheus/Grafana)
- [ ] Kubernetes

---

# Project Goal

This project is intended for learning and practicing modern backend development concepts using Java and Spring.

It demonstrates both synchronous and asynchronous communication between microservices while exploring technologies frequently used in enterprise applications.
