# Etapa de build
FROM openjdk:17-jdk-alpine as builder

WORKDIR /app

# Copia solo lo mínimo para resolver dependencias primero (mejora cacheo)
COPY pom.xml mvnw ./
COPY .mvn .mvn

RUN ./mvnw dependency:go-offline

# Ahora sí, copia el código fuente
COPY src src

# Compila la app (salta tests)
RUN ./mvnw clean package -DskipTests

# Imagen final, solo Java y el JAR ya compilado
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copia solo el .jar final, no el código ni dependencias
COPY --from=builder /app/target/*.jar app.jar

# Usa el valor de $PORT si lo pasan, o 8081 por defecto
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
