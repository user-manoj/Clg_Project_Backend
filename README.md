# Nexus CS - College Project Backend

A backend REST API for a college/student management platform built with **Spring Boot**. The application provides APIs for user authentication, faculty information, study notes, notices, previous-year question papers, online tests, profiles, and administrative user management.

## 🚀 Features

* 🔐 User registration and login
* 🎫 JWT-based authentication
* 👤 User profile management
* 👨‍🏫 Faculty information
* 📚 Study notes management
* 📢 College notices
* 📝 Previous-year question papers
* 🧪 Online tests and assessments
* 📊 Test submission and result calculation
* 👨‍💼 Admin user management
* 🗄️ MySQL database integration
* ✅ Request validation
* 🔒 Spring Security integration
* 📦 RESTful API architecture

---

## 🛠️ Tech Stack

### Backend

* **Java 17**
* **Spring Boot 4.1.0**
* Spring Web MVC
* Spring Data JPA
* Spring Security
* Spring OAuth2 Resource Server
* Spring Validation
* Lombok
* Maven

### Database

* **MySQL**
* Hibernate / JPA

### Authentication

* **JWT (JSON Web Token)**
* Spring Security

---

## 📁 Project Structure

```text
Clg_Project_Backend/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── Nexus/
│   │   │           └── Clg_Project_Backend/
│   │   │               │
│   │   │               ├── Config/
│   │   │               │   ├── SecurityConfig.java
│   │   │               │   └── WebConfig.java
│   │   │               │
│   │   │               ├── Controller/
│   │   │               │   ├── FacultyController.java
│   │   │               │   ├── MainController.java
│   │   │               │   ├── NotesController.java
│   │   │               │   ├── NoticeController.java
│   │   │               │   ├── OldQuestionPaperController.java
│   │   │               │   ├── TestController.java
│   │   │               │   └── UserControllers/
│   │   │               │
│   │   │               ├── DTO/
│   │   │               │   ├── Test/
│   │   │               │   └── UserDTO/
│   │   │               │
│   │   │               ├── Model/
│   │   │               │   ├── Faculty.java
│   │   │               │   ├── NotesEntity.java
│   │   │               │   ├── NoticeEntity.java
│   │   │               │   ├── OldQuestionPaperEntity.java
│   │   │               │   ├── QuestionEntity.java
│   │   │               │   ├── TestEntity.java
│   │   │               │   └── UserModel/
│   │   │               │
│   │   │               ├── Repo/
│   │   │               │   ├── FacultyRepository.java
│   │   │               │   ├── NotesRepository.java
│   │   │               │   ├── NoticeRepository.java
│   │   │               │   ├── OldQuestionPaperRepository.java
│   │   │               │   ├── QuestionRepository.java
│   │   │               │   ├── TestRepository.java
│   │   │               │   └── UserRepository/
│   │   │               │
│   │   │               └── Service/
│   │   │                   ├── FacultyService.java
│   │   │                   ├── NotesService.java
│   │   │                   ├── NoticeService.java
│   │   │                   ├── OldQuestionPaperService.java
│   │   │                   ├── TestService.java
│   │   │                   └── UserService/
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   ├── test/
│   │
│   └── ...
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── .gitignore
```

---

## ⚙️ Requirements

Before running the project, make sure you have installed:

* **Java JDK 17 or later**
* **MySQL 8.x**
* **Maven** (optional because Maven Wrapper is included)
* Git
* Postman or another API testing tool (optional)

Check Java installation:

```bash
java -version
```

---

## 🗄️ Database Setup

The project uses MySQL.

Create the database:

```sql
CREATE DATABASE nexuscsdb;
```

The application is configured to connect to:

```text
Database: nexuscsdb
Host: localhost
Port: 3306
Username: root
```

Hibernate is configured with:

```properties
spring.jpa.hibernate.ddl-auto=update
```

This allows Hibernate to automatically create/update the required database tables based on the entity classes.

---

## 🔧 Configuration

The application configuration is located at:

```text
src/main/resources/application.properties
```

Example configuration:

```properties
spring.application.name=Clg_Project_Backend

server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/nexuscsdb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_DATABASE_PASSWORD

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

jwt.secret=YOUR_JWT_SECRET
jwt.issuer=nexusCS
jwt.expiry=10800000
```

### ⚠️ Security Notice

**Do not commit real database passwords, JWT secrets, API keys, or other credentials to GitHub.**

For a public repository, replace sensitive values with environment variables or another secure configuration mechanism.

For example:

```properties
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

jwt.secret=${JWT_SECRET}
```

Then configure the environment variables on your local machine or deployment server.

---

## ▶️ Running the Application

### 1. Clone the repository

```bash
git clone <YOUR_REPOSITORY_URL>
```

### 2. Navigate to the backend directory

```bash
cd Clg_Project_Backend
```

### 3. Configure MySQL

Make sure MySQL is running and the database credentials in `application.properties` are correct.

### 4. Start the application

Using Maven Wrapper:

#### Windows

```bash
mvnw.cmd spring-boot:run
```

#### Linux / macOS

```bash
./mvnw spring-boot:run
```

Or using Maven:

```bash
mvn spring-boot:run
```

The backend will start at:

```text
http://localhost:8080
```

---

# 🔗 API Endpoints

The API is organized around several major modules.

## 🔐 Authentication

### Register

```http
POST /api/register
```

Used to register a new user.

### Login

```http
POST /api/login
```

Used to authenticate a user and obtain a JWT token.

Example:

```json
{
  "username": "example",
  "password": "password"
}
```

The returned JWT should be sent with protected requests using:

```http
Authorization: Bearer <JWT_TOKEN>
```

---

# 👤 Profile

### Get Current User Profile

```http
GET /api/profile/me
```

### Update Current User Profile

```http
PUT /api/profile/me
```

These endpoints allow authenticated users to view and update their profile information.

---

# 👨‍🏫 Faculty

### Get Faculty

```http
GET /api/faculty
```

Returns the available faculty information.

---

# 📚 Notes

### Upload Notes

```http
POST /api/notes/upload
```

Allows notes/files to be uploaded.

### Get Notes

```http
GET /api/notes
```

Optional subject filter:

```http
GET /api/notes?subject=Computer Science
```

### Download Note File

```http
GET /api/notes/{id}/file
```

### Delete Notes

```http
DELETE /api/notes/{id}
```

---

# 📢 Notices

### Get All Notices

```http
GET /api/notice
```

### Get Notice File

```http
GET /api/notice/{id}/file
```

### Create Notice

```http
POST /api/notice
```

### Update Notice

```http
PUT /api/notice/
```

### Delete Notice

```http
DELETE /api/notice/{id}
```

---

# 📝 Previous-Year Question Papers

### Upload Question Paper

```http
POST /api/old-question-papers/upload
```

### Get Question Papers

```http
GET /api/old-question-papers
```

Optional subject filter:

```http
GET /api/old-question-papers?subject=Computer Science
```

### Download Question Paper

```http
GET /api/old-question-papers/{id}/file
```

### Delete Question Paper

```http
DELETE /api/old-question-papers/{id}
```

---

# 🧪 Online Tests

The test module provides functionality for creating tests, retrieving questions, conducting tests, submitting answers, and viewing results.

### Create Test

```http
POST /api/tests
```

### Get All Tests

```http
GET /api/tests
```

### Get My Tests

```http
GET /api/tests/mine
```

### Get Test Questions

```http
GET /api/tests/{id}/questions
```

### Conduct Test

```http
GET /api/tests/{id}
```

### Submit Test

```http
POST /api/tests/{id}/submit
```

### Delete Test

```http
DELETE /api/tests/{id}
```

---

# 👨‍💼 Admin APIs

Administrative user-management APIs are available under:

```text
/api/admin/users
```

### Create User

```http
POST /api/admin/users
```

### List Users

```http
GET /api/admin/users
```

### Delete User

```http
DELETE /api/admin/users/{id}
```

---

# 🔒 Authentication Flow

The application uses JWT-based authentication.

The general authentication flow is:

```text
Client
  │
  │ POST /api/login
  ▼
Backend
  │
  │ Validate username/password
  ▼
JWT Token
  │
  ▼
Client
  │
  │ Authorization: Bearer <token>
  ▼
Protected API
  │
  ▼
Spring Security
  │
  ▼
Controller
```

---

# 🏗️ Architecture

The backend follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### Controller Layer

Handles HTTP requests and responses.

### Service Layer

Contains business logic.

### Repository Layer

Handles database operations using Spring Data JPA.

### Model Layer

Contains JPA entities representing database tables.

### DTO Layer

Handles structured request and response data between the client and server.

### Config Layer

Contains application configuration, security configuration, JWT configuration, and web configuration.

---

# 🧪 Testing the API

You can use tools such as:

* Postman
* Insomnia
* cURL
* Frontend applications

Example:

```bash
curl http://localhost:8080/home
```

For protected endpoints:

```bash
curl -H "Authorization: Bearer <YOUR_JWT_TOKEN>" \
     http://localhost:8080/api/profile/me
```

---

# 📦 Building the Project

To create a production build:

```bash
./mvnw clean package
```

On Windows:

```bash
mvnw.cmd clean package
```

The generated JAR file will be available inside:

```text
target/
```

Run the JAR using:

```bash
java -jar target/Clg_Project_Backend-0.0.1-SNAPSHOT.jar
```

---

# 🌱 Future Improvements

Some possible improvements for future versions:

* [ ] Swagger / OpenAPI API documentation
* [ ] Improved role-based authorization
* [ ] Refresh token support
* [ ] Email verification
* [ ] Password reset functionality
* [ ] Pagination for notes, notices, and question papers
* [ ] Cloud file storage
* [ ] Docker support
* [ ] CI/CD pipeline
* [ ] Automated integration tests
* [ ] Production environment configuration
* [ ] API rate limiting
* [ ] Improved logging and monitoring

---

# 🤝 Contributing

Contributions are welcome.

1. Fork the repository.
2. Create a new branch.

```bash
git checkout -b feature/your-feature
```

3. Make your changes.
4. Commit your changes.

```bash
git commit -m "Add your feature"
```

5. Push the branch.

```bash
git push origin feature/your-feature
```

6. Open a Pull Request.

---

# 📄 License

This project is currently intended for educational and academic purposes.

If you plan to distribute or deploy the project publicly, add an appropriate open-source license such as MIT, Apache-2.0, or GPL-3.0.

---

# 👨‍💻 Project

**Project Name:** Nexus CS
**Backend:** Clg_Project_Backend
**Language:** Java
**Framework:** Spring Boot
**Database:** MySQL
**Authentication:** JWT + Spring Security
**Java Version:** 17
**Server Port:** 8080

---

## ⭐ Support

If you find this project useful, consider giving the repository a ⭐ on GitHub.
