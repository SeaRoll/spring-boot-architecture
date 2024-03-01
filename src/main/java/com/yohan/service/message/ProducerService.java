package com.yohan.service.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

  private final ProducerGateway producerGateway;

  public void sendToMqtt(String data) {
    producerGateway.sendToMqtt(data, "testTopic");
    log.info("Message sent to MQTT broker: {}", data);
  }
}
