# Use Maven to build the application
FROM maven:3.9.9-amazoncorretto-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use OpenJDK to run the application
FROM openjdk:20-slim-bullseye
WORKDIR /app
COPY --from=build /app/target/scheduleSystems-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]