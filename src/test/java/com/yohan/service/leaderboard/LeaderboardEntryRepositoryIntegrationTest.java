package com.yohan.service.leaderboard;

import static org.hamcrest.MatcherAssert.assertThat;

import com.yohan.service.leaderboard.internal.LeaderboardEntry;
import com.yohan.service.leaderboard.internal.LeaderboardEntryRepository;
import java.util.List;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class LeaderboardEntryRepositoryIntegrationTest extends LeaderboardComponentTest {
  @Autowired LeaderboardEntryRepository repository;

  @AfterEach
  void tearDown() {
    repository.deleteAll();
  }

  @Test
  void saveAndRetrieve() {
    LeaderboardEntry entity = new LeaderboardEntry("g-looter", 100);
    repository.saveAll(List.of(entity));
    LeaderboardEntry fromRepository = repository.findById(entity.getId()).get();
    assertThat(fromRepository, Is.is(entity));
  }
}
