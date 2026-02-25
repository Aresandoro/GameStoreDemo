# Usa un'immagine base con Java 17 (o 11 se preferisci)
FROM eclipse-temurin:17-jdk-alpine

# Cartella di lavoro nel container
WORKDIR /app

# Copia i file Maven wrapper e pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copia tutto il progetto
COPY src ./src

# Rende eseguibile il wrapper
RUN chmod +x mvnw

# Costruisci il progetto
RUN ./mvnw clean package -DskipTests

# Espone la porta che Spring Boot userà (Render passa PORT)
ENV PORT=8080
EXPOSE 8080

# Comando per avviare l'applicazione
CMD ["sh", "-c", "java -jar out/artifacts/demo_jar/demo.jar"]