package com.yohan.service.message;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface ProducerGateway {
  void sendToMqtt(String data);
}
