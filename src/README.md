# Bookstore API (Spring Boot)

A complete Bookstore REST API built with Spring Boot.  
Features CRUD operations, search, pagination, global error handling, and interactive documentation with Swagger UI.

---

## 🚀 Features

- Add, view, update, and delete books
- Search books by title/author
- Paginated book listing
- Centralized error handling (`@RestControllerAdvice`)
- Swagger UI documentation (`/swagger-ui/index.html`)
- Field-level and endpoint-level API documentation

---

## 🏗️ Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- H2/MySQL/Postgres (choose your DB)
- Swagger/OpenAPI (`springdoc-openapi`)
- Maven

---

## ⚙️ Setup Instructions

1. Clone this repo:  
   `git clone https://github.com/yourusername/bookstore-api.git`
2. Build the project:  
   `mvn clean install`
3. Run the app:  
   `mvn spring-boot:run`
4. Open Swagger UI:  
   `http://localhost:8080/swagger-ui/index.html`

---

## 📚 API Endpoints

| Method | Path              | Description             | Success | Error Handling         |
|--------|-------------------|------------------------|---------|-----------------------|
| GET    | /books            | List all books         | 200     | Pagination, search    |
| POST   | /books            | Add a new book         | 201     | Validation errors     |
| GET    | /books/{id}       | Get book by ID         | 200     | 404 if not found      |
| PUT    | /books/{id}       | Update book by ID      | 200     | 404/validation errors |
| DELETE | /books/{id}       | Delete book by ID      | 204     | 404 if not found      |
| GET    | /books/search     | Search by title/author | 200     | Validation errors     |

---

## 🖼️ Screenshots

- Swagger UI main page

---

## 📝 Example Request (JSON)

