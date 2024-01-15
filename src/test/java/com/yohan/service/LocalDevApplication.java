package com.yohan.service;

import org.springframework.boot.SpringApplication;

public class LocalDevApplication {
  public static void main(String[] args) {
    SpringApplication.from(Application::main).with(LocalDevTestcontainersConfig.class).run(args);
  }
}
