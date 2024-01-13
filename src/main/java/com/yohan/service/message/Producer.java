package com.yohan.service.message;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

@Service
public class Producer {
  @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
  public interface MyGateway {
    void sendToMqtt(String data);
  }
}
