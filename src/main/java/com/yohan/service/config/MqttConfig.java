package com.yohan.service.config;

import static com.yohan.service.config.SSLUtils.getSocketFactory;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

  @Value("${spring.mqtt.broker-port}")
  private String brokerPort;

  @Value("${spring.mqtt.broker-host}")
  private String brokerHost;

  @Value("${spring.mqtt.ca-path}")
  private String caFilePath;

  @Value("${spring.mqtt.client-pem-path}")
  private String clientCrtFilePath;

  @Value("${spring.mqtt.client-key-path}")
  private String clientKeyFilePath;

  @Bean
  public MessageChannel mqttInputChannel() {
    return new DirectChannel();
  }

  @Bean
  public MessageProducer inbound() throws Exception {
    MqttPahoMessageDrivenChannelAdapter adapter =
        new MqttPahoMessageDrivenChannelAdapter(
            "ssl://" + brokerHost + ":" + brokerPort,
            "clientId",
            mqttClientFactory(),
            "testTopic",
            "topic2");
    adapter.setCompletionTimeout(5000);
    adapter.setConverter(new DefaultPahoMessageConverter());
    adapter.setQos(1);
    adapter.setOutputChannel(mqttInputChannel());
    return adapter;
  }

  public MqttPahoClientFactory mqttClientFactory() throws Exception {
    DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
    MqttConnectOptions options = new MqttConnectOptions();
    options.setServerURIs(new String[] {"ssl://" + brokerHost + ":" + brokerPort});
    options.setSocketFactory(
        getSocketFactory(caFilePath, clientCrtFilePath, clientKeyFilePath, "changeme"));
    factory.setConnectionOptions(options);
    return factory;
  }

  @Bean
  @ServiceActivator(inputChannel = "mqttOutboundChannel")
  public MessageHandler mqttOutbound() throws Exception {
    MqttPahoMessageHandler messageHandler =
        new MqttPahoMessageHandler("testClient", mqttClientFactory());
    messageHandler.setAsync(true);
    messageHandler.setDefaultTopic("testTopic");
    return messageHandler;
  }

  @Bean
  public MessageChannel mqttOutboundChannel() {
    return new DirectChannel();
  }
}
