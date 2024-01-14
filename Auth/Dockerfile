FROM maven:3.8.2-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar



ENTRYPOINT ["java" , "-jar" , "app.jar"]