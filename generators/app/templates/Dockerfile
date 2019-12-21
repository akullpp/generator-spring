FROM maven:slim AS build

WORKDIR /usr/local/src/<%= project %>/
COPY pom.xml .
RUN mvn -q -B dependency:resolve dependency:resolve-plugins
COPY src/ src/
RUN mvn -q -B package

FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8080
ENV JAVA_OPTS=""

WORKDIR /usr/local/
COPY --from=build /usr/local/src/<%= project %>/target/*.jar <%= project %>.jar
ENTRYPOINT exec java $JAVA_OPTS -jar <%= project %>.jar
