# Ecommerce Application

This is a simple ecommerce application built with Spring Boot.

## Technologies Used

- Spring Boot 2.7.12
- Java 17
- Spring Boot Actuator
- Spring Data JPA
- Spring Web
- JSON Web Token (JWT) 0.11.2
- Jackson Databind
- Spring Security
- PostgreSQL
- Lombok
- Spring Boot Test

## Description

The ecommerce application is developed using the Spring Boot framework, which provides a robust and efficient foundation for building Java applications. It utilizes various technologies and libraries to enable key functionalities.

The application includes the following features:

- **Spring Boot Actuator**: Enables monitoring and managing the application's operational aspects through built-in endpoints.
- **Spring Data JPA**: Provides support for working with relational databases using Java Persistence API (JPA) for data access.
- **Spring Web**: Facilitates the development of web applications by providing essential web-related components and functionalities.
- **JSON Web Token (JWT)**: Allows secure authentication and authorization by generating and validating JSON-based access tokens.
- **Jackson Databind**: Enables serialization and deserialization of Java objects to and from JSON format.
- **Spring Security**: Provides authentication and authorization capabilities to secure the application.
- **PostgreSQL**: A powerful open-source relational database management system used as the backend database.
- **Lombok**: A library that helps reduce boilerplate code in Java classes by providing annotations for automatic code generation.
- **Spring Boot Test**: Offers testing support for Spring Boot applications, making it easier to write unit and integration tests.

## Getting Started

To run the ecommerce application on your local machine, follow the steps below:

1. Make sure you have Java 17 or a compatible version installed on your system.

2. Ensure you have PostgreSQL installed and running. Create a new database for the application.

3. Clone the repository or download the source code.

4. Open the project in your preferred IDE.

5. Configure the database connection by modifying the `application.properties` file located in the `src/main/resources` directory. Update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties with your PostgreSQL database details.

6. Build the project using Maven. Open a terminal or command prompt, navigate to the project directory, and execute the following command:

7. Once the build is successful, you can start the application by running the following command: mvn spring-boot:run

8. The application will start, and you should see log messages indicating the server is up and running.

9. Open a web browser and access the application at `http://localhost:8080`.

10. You can now explore and interact with the ecommerce application.
