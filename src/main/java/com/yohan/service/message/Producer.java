package com.yohan.service.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {

  private final ProducerGateway producerGateway;

  public void sendToMqtt(String data) {
    producerGateway.sendToMqtt(data);
    log.info("Message sent to MQTT broker: {}", data);
  }
}
