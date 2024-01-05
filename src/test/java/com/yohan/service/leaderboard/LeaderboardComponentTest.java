package com.yohan.service.leaderboard;

import static org.springframework.modulith.test.ApplicationModuleTest.BootstrapMode.ALL_DEPENDENCIES;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import com.yohan.service.SpringBootPostgresContainer;
import java.util.function.Supplier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@ApplicationModuleTest(webEnvironment = WebEnvironment.RANDOM_PORT, mode = ALL_DEPENDENCIES)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@Testcontainers
public abstract class LeaderboardComponentTest {

  private static final PostgreSQLContainer<?> postgreSQLContainer =
      SpringBootPostgresContainer.getInstance();

  @DynamicPropertySource
  static void applicationProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", awaitContainer(postgreSQLContainer::getJdbcUrl));
    registry.add("spring.datasource.username", awaitContainer(postgreSQLContainer::getUsername));
    registry.add("spring.datasource.password", awaitContainer(postgreSQLContainer::getPassword));
  }

  static Supplier<Object> awaitContainer(Supplier<Object> getter) {
    return () -> {
      await().until(postgreSQLContainer::isRunning);
      return getter.get();
    };
  }
}
