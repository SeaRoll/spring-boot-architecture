# G-Loot code test for backend developers

## Things you will need

* Git
* Java 17
* A code editor

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

## Test

This is a sample spring boot which currently only offers a single GET endpoint at `/api/v1/leaderboard`
which will return a leaderboard consisting of entities representing players and their score.

We expect you to perform the following list of tasks:

* Extend functionality
    * Add support for multiple leaderboards
    * Add new API endpoints for:
        * For fetching the position of a user for a specific leaderboard
        * Setting score for a user in a leaderboard
    * Write tests for the code you add
* Refactor the existing method in LeaderboardService

## Sending in your submission

Clone the repository, make your changes and e-mail an archive with the result
to [codetest@gloot.com](mailto:codetest@gloot.com?subject=Spring%20Boot%20Code%20Test)

Including a commit history is a plus which would showcase your thought process
