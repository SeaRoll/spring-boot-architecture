package com.yohan.service.leaderboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.yohan.service.leaderboard.internal.LeaderboardEntry;
import com.yohan.service.leaderboard.internal.LeaderboardEntryRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LeaderboardServiceTest {

  @Mock LeaderboardEntryRepository repository;
  @InjectMocks LeaderboardService service;

  @Test
  void getLeaderboard() {
    List<LeaderboardEntry> entities =
        List.of(new LeaderboardEntry("g-looter-2", 90), new LeaderboardEntry("g-looter-1", 100));
    when(repository.findAll()).thenReturn(new ArrayList<>(entities));
    List<LeaderboardEntryDto> leaderboard = service.getListOfAllLeaderboardEntriesAsDTO();
    assertEquals(entities.size(), leaderboard.size());
  }
}
