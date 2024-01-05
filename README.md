# Spring Boot Architecture

## Things you will need

* Java 21
* IntelliJ IDEA

## General info

* Written using [spring boot](https://spring.io/projects/spring-boot)
* Uses [postgreSQL](https://www.postgresql.org/) as database
* Uses [lombok](https://projectlombok.org) which needs to be enabled/configured in your editor
* Database migrations done with [flyway](https://flywaydb.org)
* Tests done in [JUnit 5](https://junit.org/junit5/) with [Testcontainers](https://www.testcontainers.org/)
* Maven wrapper is included for building/testing
    * On Unix systems use:
      `./mvnw clean verify`
    * On Windows:
      `./mvnw.cmd clean verify`

## Pull requests requirements
* run `./mvnw fmt:format` before pushing
* run `./mvnw clean verify` before pushing