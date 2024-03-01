package com.yohan.service.message;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@Slf4j
@Configuration
public class ConsumerListener {
  @Bean
  @ServiceActivator(inputChannel = "mqttInputChannel")
  public MessageHandler mqttInputChannelHandler(ConsolePrinter consolePrinter) {
    return message -> {
      String payload = message.getPayload().toString();
      consolePrinter.logMessage(
          payload,
          Objects.requireNonNull(message.getHeaders().get("mqtt_receivedTopic")).toString());
    };
  }
}
