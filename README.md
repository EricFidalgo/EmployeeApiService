# Employee API Service

**[→ View Project Page](https://ericfidalgo.github.io/portfolio/projects/EmployeeApiService)**

## Project Description

This project is a RESTful API for managing employee records. Built using Java and the Spring Boot framework, it provides a robust backend service for performing standard CRUD (Create, Read, Update, Delete) operations. The API allows clients to retrieve a list of all employees, fetch a specific employee by their unique ID, create new employee records, update existing ones, and delete employees from the system. It is designed with a clean, layered architecture, separating concerns between the controller, service, and repository layers, and uses a MySQL database for data persistence.

## Technologies Used

- **Language:** Java
- **Core Libraries:** Spring Data JPA, Spring Web
- **Frameworks:** Spring Boot
- **Database:** MySQL
- **Testing:** JUnit 5, Mockito
- **Tools:** Gradle

## How to Run

Follow these steps to get the project running on your local machine.

1.  **Prerequisites**

    - Java 17 JDK (or newer)
    - MySQL Server
    - Git

2.  **Clone & Configure**

    You need to configure the database connection. Open the `api/src/main/resources/application.yml` file and update the datasource properties with your MySQL username and password.

    ```yaml
    spring:
      application:
        name: employees
      datasource:
        url: jdbc:mysql://localhost:3306/employees?createDatabaseIfNotExist=true
        username: your_db_username #<-- ADD YOUR DB USERNAME
        password: your_db_password #<-- ADD YOUR DB PASSWORD
      jpa:
        hibernate:
          ddl-auto: update
        database-platform: org.hibernate.dialect.MySQLDialect
        show-sql: true
    ```

    The application will create the `employees` database if it does not exist.

3.  **Build & Run**

    You can build and run the application using the included Gradle wrapper.

    On macOS/Linux:

    ```bash
    ./gradlew bootRun
    ```

    On Windows:

    ```bash
    gradlew.bat bootRun
    ```

    The API server will start on `http://localhost:8080`.

## Testing

You can run the project's test suite using the Gradle wrapper. This will execute all unit and integration tests.

On macOS/Linux:

```bash
./gradlew test
```

On Windows:

```bash
gradlew.bat test
```

## Project Output

Here are some examples of how to interact with the API using a tool like `curl` or Postman.

#### Create a new Employee

**Request:** `POST /api/v1/employees`

```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "jobTitle": "Project Manager"
}
```

**Response:** `201 Created`

```json
{
  "uuid": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
  "fullName": "Jane Doe",
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "jobTitle": "Project Manager"
}
```

#### Get All Employees

**Request:** `GET /api/v1/employees`

**Response:** `200 OK`

```json
[
  {
    "uuid": "a1b2c3d4-e5f6-7890-1234-567890abcdef",
    "fullName": "Jane Doe",
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "jane.doe@example.com",
    "jobTitle": "Project Manager"
  }
]
```

#### Delete an Employee

**Request:** `DELETE /api/v1/employees/a1b2c3d4-e5f6-7890-1234-567890abcdef`

**Response:** `204 No Content`
