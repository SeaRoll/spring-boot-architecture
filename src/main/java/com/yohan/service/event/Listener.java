package com.yohan.service.event;

import java.util.List;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Listener {
  @KafkaListener(topics = "topic1", batch = "true")
  public void listen(@Payload List<RandomEvent> foo, Acknowledgment acknowledgment) {
    for (RandomEvent event : foo) {
      System.out.println(event);
    }
    acknowledgment.acknowledge();
  }
}
