# Stage 1: build
FROM maven:3.9.11-eclipse-temurin-25-alpine AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

# Use cache layer na compilação
RUN mvn -B -DskipTests clean package

# Stage 2: runtime
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

ARG JAR_FILE=target/backend-0.0.1-SNAPSHOT.jar
COPY --from=build /app/${JAR_FILE} app.jar

ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]

EXPOSE 8080