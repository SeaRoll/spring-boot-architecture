package com.yohan.service.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsolePrinter {
  public void logMessage(String data, String topic) {
    log.info("Message received from MQTT broker: {}, {}", data, topic);
  }
}
