# Build stage: compile and run tests
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B clean test

# Final stage: keep the built artifact in a smaller image
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target ./target
CMD ["mvn", "test"]
