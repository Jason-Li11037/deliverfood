# deliverfood

A Spring Boot based online food ordering system.

## Tech stack

- Java 21
- Spring Boot 4.1.0 (Spring MVC)
- Gradle (Groovy DSL), YAML configuration
- PostgreSQL 15 (runs in Docker; schema and seed data in `src/main/resources/database-init.sql`)
- Spring Data JDBC
- Spring Security (form login, session-based)
- Spring Cache with Caffeine (in-memory)
- JUnit 5 and Mockito for tests
- React 18 and Ant Design 4 for the client (Create React App, Node.js 20 or later)

## Project structure

```
src/main/java/com/deliverfood/
├── DeliverfoodApplication.java   # Spring Boot entry point (@SpringBootApplication)
├── AppConfig.java                # Spring Security: filter chain, JDBC user store, password encoder
├── DevRunner.java                # development runner: seeds sample data and exercises the repositories on startup
├── hello/                        # first REST endpoint: HelloController and the Person/Address/Book records
├── controller/                   # REST controllers (menu, cart, customer sign-up)
├── entity/                       # Spring Data JDBC entities (customer, restaurant, menu item, order item, cart)
├── repository/                   # Spring Data JDBC repositories
├── model/                        # DTOs returned by the services (restaurant, menu item, cart, order item)
└── service/                      # business logic (restaurants and menus, cart)
src/main/resources/
├── application.yaml              # application configuration
└── database-init.sql             # database schema and seed data
docker-compose.yml                # local PostgreSQL
frontend/                         # React client (Create React App)
Dockerfile                        # container image of the application
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

On startup the application runs `database-init.sql`, which drops and recreates all tables and loads the seed data (3 restaurants and their menus). Then `DevRunner` inserts a few sample customers, carts, restaurants and menu items, exercises delete and custom update queries, and calls the services (menu lookups, adding items to a cart, clearing it). The SQL it issues is logged at DEBUG level. Because the schema is recreated on every start, this development data does not accumulate.

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

| Method | Path | Access | Description |
| --- | --- | --- | --- |
| POST | `/signup` | public | register; body `{"email", "password", "first_name", "last_name"}`, returns 201 |
| POST | `/login` | public | form login with `username` (the email) and `password`; returns 200, or 401 on bad credentials |
| POST | `/logout` | public | end the session; returns 200 |
| GET | `/restaurants/menu` | public | all restaurants, each with its menu items |
| GET | `/restaurant/{restaurantId}/menu` | public | the menu items of one restaurant |
| GET | `/cart` | signed in | the signed-in user's cart with its order items and total price |
| POST | `/cart` | signed in | add one menu item to the signed-in user's cart; body `{"menu_id": 1}` |
| POST | `/cart/checkout` | signed in | check out, which empties the cart |
| GET | `/hello?name=Guest` | signed in | returns a `Person` as JSON; `name` is optional and defaults to `Guest` |

Anything that is not listed as public returns 401 until you sign in.

```bash
# register, sign in (the session cookie is kept in cookies.txt) and call a protected endpoint
curl -X POST http://localhost:8080/signup -H "Content-Type: application/json" \
  -d '{"email": "jane@example.com", "password": "pass1234", "first_name": "Jane", "last_name": "Doe"}'
curl -c cookies.txt -X POST http://localhost:8080/login -d "username=jane@example.com&password=pass1234"
curl -b cookies.txt http://localhost:8080/cart
```

JSON keys, in both responses and request bodies, are snake_case, and `null` fields are omitted (see `application.yaml`).

Authentication uses Spring Security with form login and a server-side session. Users and their authorities are stored in the `customers` and `authorities` tables through `JdbcUserDetailsManager`, and passwords are hashed with the delegating password encoder (bcrypt by default). Sign-up lower-cases the email and creates an empty cart for the new user.

Known limitations for now:
- The sample customers created by `DevRunner` store a plain password without an encoder prefix, so they cannot sign in. Register a new user instead.
- A duplicate sign-up fails inside the database and is reported as 401 rather than a clear error.
- Credentials are sent in the clear, so use HTTPS in any real deployment.

## Frontend

The React client lives in `frontend/`. In development it runs on port 3000 and forwards API calls to the backend on port 8080 through the `proxy` setting in `frontend/package.json`, so the browser only talks to one origin and the session cookie works.

```bash
# the backend and the database must be running first
cd frontend
npm install
npm start      # http://localhost:3000
```

| File | Purpose |
| --- | --- |
| `src/utils.js` | `fetch` helpers for every backend endpoint (login, sign-up, menus, cart, checkout) |
| `src/App.js` | page layout and the signed-in state |
| `src/components/LoginForm.js` | login form; the app shows it until a sign-in succeeds |

So far the client has the layout and the login form; a successful sign-in shows a placeholder where the restaurant list and the cart will go.

Create React App is no longer maintained, but it is what the course material uses. React is pinned to version 18 because Ant Design 4 does not support React 19. The login helper sends the credentials in the query string, as the backend's form login accepts, so the client must only be used over HTTPS outside local development.

## Docker image and deployment

The `Dockerfile` packages the application jar on top of the Eclipse Temurin 21 JRE image, built for `linux/amd64`. The `.dockerignore` keeps everything except the built jar out of the build context.

```bash
./gradlew build
docker buildx build --platform=linux/amd64 -t deliverfood:latest --load .
```

Run it against the database from `docker-compose.yml`, which must already be running. Inside a container `localhost` is the container itself, so point the application at the host:

```bash
docker run --rm -p 8081:8080 \
  -e DATABASE_URL=host.docker.internal -e DATABASE_PORT=5432 -e DATABASE_PASSWORD=secret \
  deliverfood:latest
```

The application reads its database settings from environment variables (see the configuration table), so the same image works anywhere.

### Deploying to AWS (outline)

The image is meant to run on AWS App Runner with Amazon RDS for PostgreSQL and Amazon ECR as the image registry. These steps have not been run for this repository; values in angle brackets are placeholders, and real account IDs, endpoints and keys must never be committed.

1. Create an ECR repository and an RDS PostgreSQL instance (database name `deliverfood`).
2. Install the AWS CLI and run `aws configure` with an IAM user that can push to ECR.
3. Log in to the registry, tag the image and push it:
   ```bash
   aws ecr get-login-password --region <region> | docker login --username AWS --password-stdin <account-id>.dkr.ecr.<region>.amazonaws.com
   docker buildx build --platform=linux/amd64 -t <account-id>.dkr.ecr.<region>.amazonaws.com/deliverfood:latest --load .
   docker push <account-id>.dkr.ecr.<region>.amazonaws.com/deliverfood:latest
   ```
4. Create an App Runner service from that image on port 8080 and set the environment variables `DATABASE_URL` (the RDS endpoint), `DATABASE_PORT` and `DATABASE_PASSWORD`; also set `DATABASE_USERNAME` if the master user is not `postgres`.
5. Set `INIT_DB=never` once the schema exists. With the default `always`, `database-init.sql` drops and recreates every table on each start, which would wipe production data.

## Caching

`RestaurantService.getRestaurants()` and `CartService.getCart()` are cached in memory with Caffeine (`spring.cache.caffeine.spec: expireAfterWrite=60s`, so entries expire 60 seconds after they are written). Adding an item to the cart and checking out evict that customer's cart entry, so a cart is never stale after the user changes it. Changes made directly in the database can take up to a minute to show up in the restaurant list.

## Tests

```bash
./gradlew test
```

`CartServiceTests` are unit tests that mock the repositories and do not need the database. The application context test starts the whole application, so the database must be running for the full build.

## Progress

- [x] Project setup: Spring Boot application skeleton, Gradle build, YAML configuration
- [x] First REST endpoint: `/hello` with records and a request parameter
- [x] Service layer: DTOs and services for restaurants, menus and the cart (add item, view, clear)
- [x] REST API: restaurant menus and the cart
- [x] Sign-up, login and logout, with route-level authorization
- [x] Cart bound to the signed-in user
- [x] Caching with Caffeine and unit tests for the cart service
- [x] Dockerfile and deployment notes (the AWS steps are an outline and have not been run)
- [x] PostgreSQL in Docker, datasource configuration, schema and seed data
- [x] Data access with Spring Data JDBC entities and repositories
- [x] Authentication and authorization with Spring Security
- [x] React client: project setup, API helpers, layout and login form
- [ ] React client: sign-up, restaurant list and the cart

## License

Released under the [MIT License](LICENSE).
