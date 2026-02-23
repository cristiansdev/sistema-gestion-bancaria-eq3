# ---STAGE 1: BUILD ---
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiamos primero el pom para aprovechar cache
COPY pom.xml .
RUN mvn dependency:go-offline

#Copiamos el resto del código
COPY src ./src

#Compilamos
RUN mvn clean package -DskipTests

# --STAGE 2: RUNTIME ---
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiamos el jar generado desde el stage anterior
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "app.jar" ]


