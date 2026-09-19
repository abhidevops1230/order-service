# -----------------------------
# Stage 1: Build
# -----------------------------
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /build

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

RUN chmod +x mvnw

# Download dependencies first.
# This creates a reusable Docker layer.
RUN ./mvnw dependency:go-offline

COPY src src

RUN ./mvnw clean package -DskipTests


# -----------------------------
# Stage 2: Runtime
# -----------------------------
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /build/target/order-service-*.jar app.jar

# Create a non-root user
RUN useradd --system --create-home appuser

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]