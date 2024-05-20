package com.yohan.service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@Configuration
public class KafkaConfig {

  @Bean
  public RecordMessageConverter converter() {
    return new JsonMessageConverter();
  }

  @Bean
  public NewTopic topic() {
    return new NewTopic("topic1", 1, (short) 1);
  }
}
