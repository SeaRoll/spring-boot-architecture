FROM eclipse-temurin:21-jdk-alpine

RUN apk --update add openssl
COPY target/*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
