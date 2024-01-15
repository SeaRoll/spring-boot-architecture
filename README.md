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
* Uses Spring Security with JWT tokens for authentication & authorization
* Maven wrapper is included for building/testing
    * On Unix systems use:
      `./mvnw clean verify`
    * On Windows:
      `./mvnw.cmd clean verify`

## Pull requests requirements

* run `./mvnw fmt:format` before pushing
* run `./mvnw clean verify` before pushing

## Running the application locally

Navigate to the root of test folder,
And then run the `LocalDevApplication` class.
This will start all necessary docker containers
and run the application locally.

## Example of security:

```sh
curl -s --location "http://localhost:8080/auth/token" \
> --header "Content-Type: application/json" \
> --data "{
>     \"sub\": \"123\",
>     \"scope\": [
>         \"role1\",
>         \"role2\",
>         \"GUEST\"
>     ]
> }"

eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJzdWIiOiJkbWl0cnkiLCJleHAiOjE3MDM5NTg4MjcsImlhdCI6MTcwMzk1ODIyNywic2NvcGUiOlsicm9sZTEiLCJyb2xlMiIsIkdVRVNUIl19.sgwvUVJazeEdhM1Vy8eXGjvGIXkAYWFfRg_VaNpISdU
```

```sh
curl -s --location "http://localhost:8080/greeting" \
--header "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJzdWIiOiJkbWl0cnkiLCJleHAiOjE3MDM5NTg4MjcsImlhdCI6MTcwMzk1ODIyNywic2NvcGUiOlsicm9sZTEiLCJyb2xlMiIsIkdVRVNUIl19.sgwvUVJazeEdhM1Vy8eXGjvGIXkAYWFfRg_VaNpISdU"

Hello, 123. You have next permissions: [SCOPE_role1, SCOPE_role2, SCOPE_GUEST]
```
