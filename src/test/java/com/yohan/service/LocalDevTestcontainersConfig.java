package com.yohan.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class LocalDevTestcontainersConfig {

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgreSQLContainer(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("app.hello.world", () -> "hello");
    return new PostgreSQLContainer<>("postgres:14");
  }
}
