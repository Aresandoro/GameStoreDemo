# Base image Java 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia tutto il progetto
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# Build
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Porta dinamica
ENV PORT=8080
EXPOSE 8080

# Avvio jar
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]