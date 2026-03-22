# Etapa 1: Construcción (Build)
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Runtime)
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Configuración del puerto para Railway (8080)
EXPOSE 8080

# Ejecución de la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
