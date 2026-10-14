# Spring Boot + MySQL Web Application

Hi, I'm **Samina**, and I'm learning **Java and Spring Boot**. This project is part of my learning journey and demonstrates how to build a simple web application that saves and retrieves form data using Spring Boot and MySQL.

## About the Project

The application lets users enter a person's name, email, and city through a web form. Spring Boot provides REST API endpoints, and MySQL stores the submitted records.

### Features

- HTML form to enter a name, email address, and city
- Spring Boot REST API
- Save records to MySQL
- Retrieve all saved records
- Retrieve a record by its ID
- Automatically create the `people` table using Hibernate/JPA when configured
- Optionally request database creation through the MySQL connection URL, if the MySQL user has sufficient privileges
- Build and run an executable JAR
- Configure database connection details and server port at runtime
- Test API endpoints using `curl`

## Technologies Used

- Java 17+
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA
- MySQL Connector/J
- MySQL
- HTML, CSS, and JavaScript
- Maven

## Project Structure

```text
springboot-mysql-app/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── mysqlapp/
        │               ├── MysqlAppApplication.java
        │               ├── Person.java
        │               ├── PersonRepository.java
        │               ├── PersonService.java
        │               └── PersonController.java
        └── resources/
            ├── application.properties
            └── static/
                └── index.html
```

## Prerequisites

Before running the project, install:

- JDK 17 or later
- MySQL Server
- Maven (or use the Maven Wrapper if it is included in the repository)

Check that Java and Maven are available:

```bash
java -version
mvn -version
```

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/SaminaMariyam2005/springboot-mysql-app.git
cd springboot-mysql-app
```

### 2. Start MySQL

Make sure the MySQL server is running. The project's default database URL uses `springboot_demo` and requests creation of the database if it does not exist and the configured MySQL user has permission.

You can also create the database manually in MySQL:

```sql
CREATE DATABASE IF NOT EXISTS springboot_demo;
```

### 3. Configure the database connection

Open:

```text
src/main/resources/application.properties
```

The example configuration from the project uses these environment-variable overrides and defaults:

```properties
spring.application.name=springboot-mysql-app
server.port=${SERVER_PORT:8080}

spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/springboot_demo?createDatabaseIfNotExist=true}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=${DDL_AUTO:update}
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

Set the username and password to match your local MySQL setup. The default password is empty, so configure `DB_PASSWORD` if your MySQL account requires a password. Keep passwords private and never commit real credentials to GitHub.

The application uses Hibernate/JPA to create or update the `people` table when `DDL_AUTO=update`. The MySQL server must already be running and accessible.

### 4. Run the application

From the project root, run:

```bash
mvn spring-boot:run
```

When the application starts, open this URL in your browser:

**http://localhost:8080/**

The page provides a form to save a person and a table to view saved records.

## API Endpoints

The REST API base path is `/api/people`.

| HTTP Method | Endpoint | Purpose |
|---|---|---|
| `GET` | `/api/people` | Retrieve all people |
| `GET` | `/api/people/{id}` | Retrieve one person by ID |
| `POST` | `/api/people` | Create a person record |

### Example: Create a record

Send a `POST` request with JSON:

```json
{
  "name": "Ram",
  "email": "ram@example.com",
  "city": "Delhi"
}
```

Example using `curl`:

```bash
curl.exe -i -X POST http://localhost:8080/api/people -H "Content-Type: application/json" -d "{\"name\":\"Ram\",\"email\":\"ram@example.com\",\"city\":\"Delhi\"}"
```

A successful creation returns HTTP status `201 Created` and the saved record, including its generated ID.

### Retrieve all records

```bash
curl http://localhost:8080/api/people
```

### Retrieve one record by ID

```bash
curl http://localhost:8080/api/people/1
```

The API requires the name, email, and city fields. It returns a bad-request response if required fields are missing or exceed the allowed lengths.

## Build an Executable JAR

To build the project, run:

```bash
mvn clean package
```

The expected JAR path is:

```text
target/springboot-mysql-app.jar
```

Run the packaged application with:

```bash
java -jar target/springboot-mysql-app.jar
```

Maven is needed to build the application, but it is not needed on the deployment machine to run the packaged JAR. A running and reachable MySQL server is still required.

## Run with Custom MySQL Settings (Windows PowerShell)

You can configure another database and port without rebuilding the JAR:

```powershell
$env:DB_URL = "jdbc:mysql://localhost:3306/company_db?createDatabaseIfNotExist=true"
$env:DB_USERNAME = "app_user"
$env:DB_PASSWORD = "your_password"
$env:SERVER_PORT = "8085"

java -jar target/springboot-mysql-app.jar
```

Replace the sample database details with your own. The MySQL user must have the required permissions. If you use port `8085`, open **http://localhost:8085/**.

Do not use real production passwords in public documentation or commit them to the repository.

## Verify the Data in MySQL

You can check the database and records using MySQL Workbench or the MySQL command line:

```sql
USE springboot_demo;
SHOW TABLES;
DESCRIBE people;
SELECT * FROM people;
```

Records submitted through the browser form or REST API should appear in the `people` table.

## Troubleshooting

| Problem | What to check |
|---|---|
| MySQL connection refused | Confirm MySQL is running and the host/port are correct. |
| Access denied | Check the MySQL username, password, and permissions. |
| Database does not exist | Create the database manually or check database-creation permissions. |
| Port already in use | Change `SERVER_PORT` to an available port. |
| Table not found | Check the application logs and the `DDL_AUTO` setting. |
| `404` at `/` | Confirm `index.html` is located at `src/main/resources/static/index.html`. |
| `400 Bad Request` | Check the JSON body and required field names: `name`, `email`, and `city`. |
| JAR fails to start | Check the Java version and run `mvn clean package` again. |

## What I'm Learning

Through this project, I'm practising:

- Java and Spring Boot application structure
- Creating REST APIs
- Connecting Spring Boot to MySQL
- Using Spring Data JPA repositories
- Working with HTML forms and JSON requests
- Building and running a Maven project

## Author

**Samina Mariyam**  
Currently learning Java and Spring Boot.

## Repository

[View this project on GitHub](https://github.com/SaminaMariyam2005/springboot-mysql-app)

## Note

This is a learning project. Before using an application with real users, add appropriate authentication and authorization, HTTPS, stronger validation, secure secret management, database backups, and a controlled database migration strategy.
