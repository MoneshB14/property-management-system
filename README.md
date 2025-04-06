# 🏠 Property Management System

A Spring Boot-based backend system for managing real estate properties. This application follows the **CQRS (Command Query Responsibility Segregation)** pattern and uses **MongoDB** for storage, including an event store. It features secure manager authentication using **AES encryption**, proper validation, and RESTful APIs for all key operations.

---

## 🚀 Features

- ✅ Add, update, and retrieve properties
- 🔐 Secure password encryption using AES
- 🧮 Calculate manager salary (10% of total rental income)
- 🧠 Query-based endpoints:
  - Get all properties
  - Get only occupied properties
  - Filter properties by area
  - Find property with the lowest value
- 🧾 Event sourcing for tracking key actions
- 🧱 Uses CQRS for clean command-query separation
- 📦 MongoDB for data + event storage

---

## 🧰 Tech Stack

- **Java 17**
- **Spring Boot 3**
- **MongoDB Atlas**
- **Lombok**
- **Jakarta Validation**
- **Swagger/OpenAPI** (for API docs)
- **AES Encryption**
- **Maven**

---

## 📂 Project Structure

📦property-management-system
 ┣ 📂command
 ┃ ┣ 📂handler            → Command handlers (create, update, calculate)
 ┃ ┗ 📂model              → Command model classes
 ┣ 📂query
 ┃ ┗ 📂handler            → Query handlers (read-only operations)
 ┣ 📂controller           → REST controllers for all endpoints
 ┣ 📂dto                  → Data Transfer Objects for request/response
 ┣ 📂entity               → MongoDB entity classes (Property, Manager, etc.)
 ┣ 📂repository           → MongoDB repositories
 ┣ 📂utils                → Utility classes (e.g., AES encryption)
 ┣ 📂config               → Configuration classes (Swagger, Mongo, etc.)
 ┣ 📂properties           → Property-related helper classes
 ┣ 📂response             → Custom response wrappers (if any)
 ┗ 📜application.yml      → Main Spring Boot config file


🔐 Manager Authentication
Passwords are encrypted before storage and decrypted for validation using AES. The secret key and algorithm details are stored in application.yml as environment variables.

📡 API Endpoints
| Method | Endpoint                           | Description                     |
|--------|------------------------------------|---------------------------------|
| POST   | /api/properties/add                | Add a new property              |
| PUT    | /api/properties/update/{id}        | Update property (auth required)|
| POST   | /api/properties/salary             | Calculate manager salary        |
| GET    | /api/properties/all                | Get all properties              |
| GET    | /api/properties/occupied           | Get only occupied properties    |
| GET    | /api/properties/area               | Get properties by area          |
| GET    | /api/properties/lowest-value       | Get lowest value property       |


🧪 Swagger UI
After running the application, go to: http://localhost:8023/swagger-ui/index.html


✍️ Author
Monesh B
Java Backend Developer | Spring Boot | MongoDB | REST APIs
