package com.yohan.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.kafka.ConfluentKafkaContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class LocalDevTestcontainersConfig {

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgreSQLContainer() {
    return new PostgreSQLContainer<>("postgres:17.4");
  }

  @Bean
  @ServiceConnection
  ConfluentKafkaContainer kafkaContainer() {
    return new ConfluentKafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));
  }
}
