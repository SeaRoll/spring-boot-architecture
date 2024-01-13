package com.yohan.service;

import org.slf4j.event.Level;
import org.testcontainers.hivemq.HiveMQContainer;
import org.testcontainers.utility.DockerImageName;

public class SpringBootHiveMQContainer {

  private static HiveMQContainer container;

  public static HiveMQContainer getInstance() {
    if (container == null) {
      container =
          new HiveMQContainer(DockerImageName.parse("hivemq/hivemq4").withTag("2021.3"))
              .withLogLevel(Level.DEBUG);
      container.start();
    }
    return container;
  }
}
