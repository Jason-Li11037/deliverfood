# deliverfood

A Spring and Hibernate based online food ordering system.

## Tech stack

- Java 21
- Spring Boot 4.1.0 (Spring MVC)
- Gradle (Groovy DSL), YAML configuration
- PostgreSQL (schema and seed data in `src/main/resources/database-init.sql`)

## Project structure

```
src/main/java/com/deliverfood/
├── DeliverfoodApplication.java   # Spring Boot entry point (@SpringBootApplication)
├── controller/                   # REST controllers
├── entity/                       # domain entities (customer, cart, menu item, order item)
└── repository/                   # data access
src/main/resources/
├── application.yaml              # application configuration
└── database-init.sql             # database schema and seed data
```

## Getting started

Requirements: JDK 21.

```bash
./gradlew build      # compile and run tests
./gradlew bootRun    # start the application on http://localhost:8080
```

## Progress

- [x] Project setup: Spring Boot application skeleton, Gradle build, YAML configuration
- [ ] REST API: registration, menu search, ordering, checkout
- [ ] Data access with Hibernate
- [ ] Authentication and authorization with Spring Security
- [ ] React + Ant Design client
