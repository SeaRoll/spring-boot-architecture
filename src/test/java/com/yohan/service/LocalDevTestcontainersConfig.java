package com.yohan.service;

import java.io.File;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.PostgreSQLContainer;

// REF: https://medium.com/javarevisited/configuring-rabbitmq-mqtt-with-tls-ca1dcbc397d3
@TestConfiguration(proxyBeanMethods = false)
public class LocalDevTestcontainersConfig {

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgreSQLContainer(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("app.hello.world", () -> "hello");
    return new PostgreSQLContainer<>("postgres:14");
  }

  @Bean
  DockerComposeContainer<?> rabbitMqContainer(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add(
        "spring.mqtt.ca-path", () -> "./src/test/resources/rabbitmq/cert/cacert.pem");
    dynamicPropertyRegistry.add(
        "spring.mqtt.client-pem-path", () -> "./src/test/resources/rabbitmq/cert/client.pem");
    dynamicPropertyRegistry.add(
        "spring.mqtt.client-key-path", () -> "./src/test/resources/rabbitmq/cert/client.key");

    return new DockerComposeContainer<>(new File("src/test/resources/rabbitmq/docker-compose.yml"))
        .withExposedService("rabbitmq", 8883)
        .withRemoveImages(DockerComposeContainer.RemoveImages.LOCAL)
        .withBuild(true);
  }
}
