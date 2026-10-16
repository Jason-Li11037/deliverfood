# deliverfood

A Spring Boot based online food ordering system.

## Tech stack

- Java 21
- Spring Boot 4.1.0 (Spring MVC)
- Gradle (Groovy DSL), YAML configuration
- PostgreSQL 15 (runs in Docker; schema and seed data in `src/main/resources/database-init.sql`)
- Spring Data JDBC

## Project structure

```
src/main/java/com/deliverfood/
├── DeliverfoodApplication.java   # Spring Boot entry point (@SpringBootApplication)
├── DevRunner.java                # development runner: seeds sample data and exercises the repositories on startup
├── hello/                        # first REST endpoint: HelloController and the Person/Address/Book records
├── controller/                   # REST controllers
├── entity/                       # Spring Data JDBC entities (customer, restaurant, menu item, order item, cart)
├── repository/                   # Spring Data JDBC repositories
├── model/                        # DTOs returned by the services (restaurant, menu item, cart, order item)
└── service/                      # business logic (restaurants and menus, cart)
src/main/resources/
├── application.yaml              # application configuration
└── database-init.sql             # database schema and seed data
docker-compose.yml                # local PostgreSQL
practice/
└── java-basics/                  # small standalone Java exercises (not part of the Gradle build)
```

## Getting started

Requirements: JDK 21 and Docker.

```bash
docker compose up -d   # start PostgreSQL on localhost:5432
./gradlew build        # compile and run tests (the tests need the database)
./gradlew bootRun      # start the application on http://localhost:8080
docker compose down    # stop the database (data is kept in a Docker volume)
```

On startup the application runs `database-init.sql`, which drops and recreates all tables and loads the seed data (3 restaurants and their menus). Then `DevRunner` inserts a few sample customers, carts, restaurants and order items and exercises save, delete and custom update queries. The SQL it issues is logged at DEBUG level. Because the schema is recreated on every start, this development data does not accumulate.

### Configuration

The datasource is configured in `application.yaml` and can be overridden with environment variables:

| Variable | Default | Description |
| --- | --- | --- |
| `DATABASE_URL` | `localhost` | database host |
| `DATABASE_PORT` | `5432` | database port |
| `DATABASE_USERNAME` | `postgres` | database user |
| `DATABASE_PASSWORD` | `secret` | database password (local development default) |
| `INIT_DB` | `always` | set to `never` to skip running `database-init.sql` |

If port 5432 is already in use on your machine, change the host side of the port mapping in `docker-compose.yml` (for example `"5433:5432"`) and set `DATABASE_PORT=5433`.

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
- [x] Service layer: DTOs and services for restaurants, menus and the cart (add item, view, clear)
- [ ] REST API: registration, menu search, ordering, checkout
- [x] PostgreSQL in Docker, datasource configuration, schema and seed data
- [x] Data access with Spring Data JDBC entities and repositories
- [ ] Authentication and authorization with Spring Security
- [ ] React + Ant Design client
