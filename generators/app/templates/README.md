# <%= project %>

## Run

### Docker

Environment variables can be passed by setting `JAVA_OPTS`.

```sh
docker run -it $(docker build -q .)
```

### Docker Compose

```sh
docker-compose up -d
```

## Development

Please use the `dev` profile.

### PostgreSQL

```sh
docker run -d -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=<%= project %> -p 5432:5432 postgres
```

### IDEA

Ensure that you have annotation processing activated for the processing of MapStruct's and Lombok's annotations.
