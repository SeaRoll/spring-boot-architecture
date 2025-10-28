package com.yohan.service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
public class EventController {

  private final KafkaTemplate<String, BaseEvent> kafkaTemplate;

  @GetMapping("/send")
  public void send() {
    kafkaTemplate.send("topic1", new BaseEvent.NewRandomEvent(42));
  }
}
