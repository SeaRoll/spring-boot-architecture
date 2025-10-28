package com.yohan.service.event;

import java.util.List;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Listener {

  @KafkaListener(topics = "topic1", batch = "true")
  public void listen(@Payload List<BaseEvent> foo, Acknowledgment acknowledgment) {
    for (BaseEvent event : foo) {
      if (event instanceof BaseEvent.NewRandomEvent newRandomEvent) {
        handleNewRandomEvent(newRandomEvent);
      }
    }
    acknowledgment.acknowledge();
  }

  public void handleNewRandomEvent(BaseEvent.NewRandomEvent event) {
    System.out.println("Handling NewRandomEvent: " + event);
  }
}
