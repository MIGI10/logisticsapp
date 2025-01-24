FROM maven:3.9.5-amazoncorretto-21 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.0

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 3000

HEALTHCHECK --interval=10s --timeout=10s --start-period=15s --retries=10 \
  CMD curl -f http://localhost:3000/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
