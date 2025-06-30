FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app-producer-app
COPY . .
ENV KAFKA_BROKER=kafka:9092
RUN mvn clean package

FROM openjdk:17-jdk-alpine
WORKDIR /app-producer-app
COPY --from=build /app-producer-app/target/producer-app-1.0.0.jar /app-producer-app/aplicacion.jar
ENV KAFKA_BROKER=kafka:9092
CMD ["java", "-jar", "/app-producer-app/aplicacion.jar"]
