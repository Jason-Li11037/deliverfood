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
├── hello/                        # first REST endpoint: HelloController and the Person/Address/Book records
├── controller/                   # REST controllers
├── entity/                       # domain entities (customer, cart, menu item, order item)
└── repository/                   # data access
src/main/resources/
├── application.yaml              # application configuration
└── database-init.sql             # database schema and seed data
practice/
└── java-basics/                  # small standalone Java exercises (not part of the Gradle build)
```

## Getting started

Requirements: JDK 21.

```bash
./gradlew build      # compile and run tests
./gradlew bootRun    # start the application on http://localhost:8080
```

## API

| Method | Path | Description |
| --- | --- | --- |
| GET | `/hello?name=Guest` | returns a `Person` as JSON; `name` is optional and defaults to `Guest` |

```bash
curl "http://localhost:8080/hello?name=Jason"
```

JSON keys are snake_case and `null` fields are omitted (see `application.yaml`).

## Progress

- [x] Project setup: Spring Boot application skeleton, Gradle build, YAML configuration
- [x] First REST endpoint: `/hello` with records and a request parameter
- [ ] REST API: registration, menu search, ordering, checkout
- [ ] Data access with Hibernate
- [ ] Authentication and authorization with Spring Security
- [ ] React + Ant Design client
