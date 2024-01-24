package com.yohan.service.message;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class ProducerController {
  private final ProducerService producerService;

  @PostMapping
  public void sendToMqtt(@RequestBody String data) {
    producerService.sendToMqtt(data);
  }
}
