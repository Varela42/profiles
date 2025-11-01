# Usa la imagen oficial de Maven para compilar
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copia los archivos del proyecto
COPY pom.xml .
COPY src ./src

# Construye el proyecto y empaqueta el jar
RUN mvn clean package -DskipTests

# Usa una imagen de Java más ligera para ejecutar la app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia el jar generado desde la fase de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto (opcional, según tu app)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]

COPY --from=build /app/target/*.jar app.jar
