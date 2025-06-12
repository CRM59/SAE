FROM maven:3.9-eclipse-temurin-17-alpine
ARG JAR_FILE=target/*.jar
COPY .. .
RUN mvn compile package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/target/app-0.0.1-SNAPSHOT.jar"]