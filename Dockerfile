FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/inditex-backend.jar app.jar

EXPOSE 3000

HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost:3000/api/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
