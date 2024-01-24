package com.yohan.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.hivemq.HiveMQContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class LocalDevTestcontainersConfig {

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgreSQLContainer(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("app.hello.world", () -> "hello");
    return new PostgreSQLContainer<>("postgres:14");
  }

  @Bean
  HiveMQContainer hiveMQContainer(DynamicPropertyRegistry dynamicPropertyRegistry) {
    var container = new HiveMQContainer(DockerImageName.parse("hivemq/hivemq4:4.24.0"));
    dynamicPropertyRegistry.add("spring.mqtt.broker-port", container::getMqttPort);
    dynamicPropertyRegistry.add("spring.mqtt.broker-host", container::getHost);
    return container;
  }
}
