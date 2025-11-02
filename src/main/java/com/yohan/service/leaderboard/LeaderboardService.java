package com.yohan.service.leaderboard;

import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

  private final LeaderboardEntryRepository repository;

  public List<LeaderboardEntryDto> getLeaderboardEntries() {
    return repository
      .findAll()
      .stream()
      .map(entry ->
        new LeaderboardEntryDto(
          entry.getId(),
          entry.getNick(),
          entry.getScore()
        )
      )
      .sorted(Comparator.comparing(LeaderboardEntryDto::score).reversed())
      .toList();
  }
}
