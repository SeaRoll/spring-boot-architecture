package com.yohan.service;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class SpringBootPostgresContainer {

  @Container private static PostgreSQLContainer<?> container;

  public static PostgreSQLContainer<?> getInstance() {
    if (container == null) {
      container =
          new PostgreSQLContainer<>("postgres:14")
              .withDatabaseName("eis")
              .withUsername("postgres")
              .withPassword("postgres");
      container.start();
    }
    return container;
  }
}
