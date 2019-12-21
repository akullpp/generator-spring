# <%= project %>

## Run

### Docker

Environment variables can be passed by setting `JAVA_OPTS`.

```sh
docker run -it $(docker build -q .)
```

## IDEA

Ensure that you have annotation processing activated for the processing of MapStruct's and Lombok's annotations.
