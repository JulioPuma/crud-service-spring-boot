FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app-consumer-app
COPY . .
ENV KAFKA_BROKER=kafka:9092
RUN mvn clean package

FROM openjdk:17-jdk-alpine
WORKDIR /app-consumer-app
COPY --from=build /app-consumer-app/target/consumer-app-1.0.0.jar /app-consumer-app/aplicacion.jar
ENV KAFKA_BROKER=kafka:9092
CMD ["java", "-jar", "/app-consumer-app/aplicacion.jar"]
