package com.yohan.service.leaderboard;

import com.yohan.service.leaderboard.internal.LeaderboardEntryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaderboardService {

  private final LeaderboardEntryRepository repository;

  public List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO() {
    return repository.findAll().stream().map(entry -> LeaderboardEntryDto.toDto(1, entry)).toList();
  }
}
