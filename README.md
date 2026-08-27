# Nexus CS — College Management Platform Backend

A **Spring Boot REST API** for a college management platform that provides authentication, student resources, faculty information, notices, previous-year question papers, and online tests.

Built as a college project with a focus on **REST API design, JWT authentication, Spring Security, JPA/Hibernate, and MySQL**.

## 🚀 Features

* 🔐 User registration & JWT-based login
* 👤 User profile management
* 👨‍🏫 Faculty information
* 📚 Study notes with file upload/download
* 📢 College notices
* 📝 Previous-year question papers
* 🧪 Online tests, submissions & results
* 👨‍💼 Admin user management
* 🔒 Protected APIs using Spring Security
* ✅ Request validation
* 🗄️ MySQL database integration

## 🛠️ Tech Stack

**Backend**

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* OAuth2 Resource Server
* Bean Validation
* Lombok
* Maven

**Database**

* MySQL
* Hibernate / JPA

**Authentication**

* JWT
* Spring Security

## 🏗️ Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL Database
```

### Project Structure

```text
src/main/java/com/Nexus/Clg_Project_Backend/

├── Config/          # Security & application configuration
├── Controller/      # REST API endpoints
├── DTO/             # Request/response DTOs
├── Model/           # JPA entities
├── Repo/            # Spring Data repositories
└── Service/         # Business logic
```

## 🔗 Main API Modules

| Module          | Example Endpoints                            |
| --------------- | -------------------------------------------- |
| Authentication  | `POST /api/register`, `POST /api/login`      |
| Profile         | `GET /api/profile/me`, `PUT /api/profile/me` |
| Faculty         | `GET /api/faculty`                           |
| Notes           | `/api/notes`                                 |
| Notices         | `/api/notice`                                |
| Question Papers | `/api/old-question-papers`                   |
| Tests           | `/api/tests`                                 |
| Admin           | `/api/admin/users`                           |

Protected endpoints require:

```http
Authorization: Bearer <JWT_TOKEN>
```

## ⚙️ Getting Started

### Prerequisites

Make sure you have:

* **Java 17+**
* **MySQL 8+**
* Git

### 1. Clone the repository

```bash
git clone <YOUR_REPOSITORY_URL>
cd Clg_Project_Backend
```

### 2. Create the database

Open MySQL and run:

```sql
CREATE DATABASE nexuscsdb;
```

### 3. Configure the application

Update:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nexuscsdb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_DATABASE_PASSWORD

jwt.secret=YOUR_JWT_SECRET
jwt.issuer=nexusCS
jwt.expiry=10800000
```

For security, keep passwords and JWT secrets out of GitHub. Prefer environment variables for production.

### 4. Run the application

**Windows**

```bash
mvnw.cmd spring-boot:run
```

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

Or, if Maven is installed:

```bash
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

## 🧪 API Testing

You can test the APIs using **Postman**, **Insomnia**, or **cURL**.

Example:

```bash
curl http://localhost:8080/home
```

For authenticated APIs:

```bash
curl -H "Authorization: Bearer <JWT_TOKEN>" \
     http://localhost:8080/api/profile/me
```

## 📦 Build

Create a production JAR:

```bash
./mvnw clean package
```

Run it with:

```bash
java -jar target/Clg_Project_Backend-0.0.1-SNAPSHOT.jar
```

## 🔐 Security

The application uses:

* JWT-based authentication
* Spring Security
* Protected API endpoints
* Request validation
* Environment-based configuration for sensitive credentials

> **Note:** This project is developed for educational purposes.
