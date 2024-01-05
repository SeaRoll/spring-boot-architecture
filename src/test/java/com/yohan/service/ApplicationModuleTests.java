package com.yohan.service;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ApplicationModuleTests {
  @Test
  void verifyApplicationModules() {
    ApplicationModules.of(Application.class).verify();
  }
}
