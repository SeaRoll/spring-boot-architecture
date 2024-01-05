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
        List.of(new LeaderboardEntry("g-looter-2", 90), new LeaderboardEntry("g-looter-1", 100));

    when(repository.findAll()).thenReturn(new ArrayList<>(entities));

    List<LeaderboardEntryDto> leaderboard = service.getListOfAllLeaderboardEntriesAsDTO();
    assertEquals(entities.size(), leaderboard.size());
    // Verify ordering by score
    assertEqual(1, entities.get(1), leaderboard.get(0));
    assertEqual(2, entities.get(0), leaderboard.get(1));
  }

  private void assertEqual(int pos, LeaderboardEntry entity, LeaderboardEntryDto dto) {
    assertEquals(pos, dto.getPosition());
    assertEquals(entity.getNick(), dto.getNick());
    assertEquals(entity.getScore(), dto.getScore());
  }
}
