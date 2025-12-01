package com.yohan.service.leaderboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LeaderboardServiceTest {

  @Mock LeaderboardEntryRepository repository;

  @InjectMocks LeaderboardService service;

  @Test
  void getLeaderboard() {
    List<LeaderboardEntry> entities =
        List.of(LeaderboardEntry.of("player1", 300), LeaderboardEntry.of("player2", 200));

    when(repository.findAll()).thenReturn(new ArrayList<>(entities));

    List<LeaderboardEntryDto> leaderboard = service.getLeaderboardEntries();
    assertEquals(entities.size(), leaderboard.size());
  }
}
