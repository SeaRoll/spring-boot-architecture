package com.yohan.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

class ApplicationTests extends SpringBootComponentTest {

  @Value("${app.hello.world}")
  String helloWorld;

  @Test
  void contextLoads() {
    Assertions.assertFalse(helloWorld.isEmpty());
  }
}
