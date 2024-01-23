package com.yohan.service.condition;

import com.yohan.service.SpringBootComponentTest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ConditionRepositoryTests extends SpringBootComponentTest {

  @Autowired private ConditionRepository repository;

  private final List<Map<String, String>> DEVICES =
      List.of(
          Map.of("company", "companyA", "device", "deviceA"),
          Map.of("company", "companyA", "device", "deviceB"),
          Map.of("company", "companyB", "device", "deviceC"),
          Map.of("company", "companyB", "device", "deviceD"));

  private Condition generateRandomCondition(Instant time) {
    var randomDevice = DEVICES.get(new Random().nextInt(DEVICES.size()));

    return Condition.builder()
        .time(time)
        .company(randomDevice.get("company"))
        .device(randomDevice.get("device"))
        .value(Map.of("temperature", new Random().nextDouble() * 100))
        .build();
  }

  @Test
  void test() {
    System.out.println("Inserting");
    List<Condition> conditions = new ArrayList<>();

    for (int i = 0; i < 100000; i++) {
      // add day by 1000 entries
      int day = i / 1000;
      conditions.add(generateRandomCondition(Instant.now().plusSeconds(day * 24 * 60 * 60)));
    }

    System.out.println("Saving");
    repository.saveAll(conditions);

    System.out.println("Querying");
    var resp =
        repository.getAvgValuePerDay(
            "1 day",
            "temperature",
            "companyA",
            Instant.now(),
            Instant.now().plusSeconds(3 * 24 * 60 * 60));
    resp.forEach(r -> System.out.println(r.getBucket() + " " + r.getAvgValue()));
    var exp =
        repository.explainGetAvgValueDevicePerDay(
            "1 day",
            "temperature",
            "companyA",
            "deviceA",
            Instant.now(),
            Instant.now().plusSeconds(3 * 24 * 60 * 60));
    System.out.println(exp);
    Assertions.assertEquals(3, resp.size());
  }
}
