# Generator for Spring Projects

## Usage

```sh
git clone https://github.com/akullpp/generator-spring.git
cd generator-spring
npm link
yo spring
```

The assumption is that you have a PostgreSQL database running at `localhost:5432` with postgres/postgres credentials and the artifact's name as database name. This can be changed in the `application-dev.yml`.

## Stack

### Production

- Spring Data JPA
- Spring Starter Web
- PostgreSQL
- Swagger with UI

### Development

- Spring Configuration Processor
- Flyway
- Lombok
- MapStruct
- JUnit 5
- AssertJ
- Mockito
