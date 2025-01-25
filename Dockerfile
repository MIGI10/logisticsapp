FROM maven:3.9.5-amazoncorretto-21 AS build

WORKDIR /app

COPY pom.xml .

# Pre-fetch all Maven dependencies and store them in the local cache
RUN mvn dependency:go-offline -B

COPY src ./src

# Build the application, skipping tests to speed up the build
RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.0

WORKDIR /app

# Copy the built JAR file from the build stage to the current stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 3000

# Define a health check command that will regularly verify the service's health endpoint
HEALTHCHECK --interval=10s --timeout=10s --start-period=15s --retries=10 \
  CMD curl -f http://localhost:3000/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
