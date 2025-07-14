# Etapa 1: construcción dentro de un contenedor Maven
FROM maven:3.9.0-eclipse-temurin-17 AS builder
WORKDIR /app

# 1. Copiamos el pom y el código fuente
COPY pom.xml .
COPY src ./src

# 2. Empaquetamos el proyecto sin tests
RUN mvn clean package -DskipTests

# Etapa 2: runtime con JRE 17 ligero
FROM eclipse-temurin:17-jdk
WORKDIR /app

# 3. Copiamos el JAR ya construido
COPY --from=builder /app/target/*.jar app.jar

# 4. Indicamos cómo arrancar la app
ENTRYPOINT ["java","-jar","app.jar"]
