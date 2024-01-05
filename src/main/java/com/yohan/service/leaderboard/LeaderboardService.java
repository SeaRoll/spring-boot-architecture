package com.yohan.service.leaderboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeaderboardService {

  private final LeaderboardEntryRepository repository;

  // TODO: Refactor this method
  public List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO() {
    List<LeaderboardEntry> allEntries = repository.findAll();
    Collections.sort(
        allEntries,
        new Comparator<LeaderboardEntry>() {
          public int compare(LeaderboardEntry e1, LeaderboardEntry e2) {
            return e2.getScore() - e1.getScore();
          }
        });

    LeaderboardEntry[] allEntriesAsEntities = allEntries.toArray(new LeaderboardEntry[] {});
    LeaderboardEntryDto[] dtoObjects = new LeaderboardEntryDto[allEntriesAsEntities.length];

    for (int i = allEntriesAsEntities.length - 1; i >= 0; i--) {
      dtoObjects[i] = LeaderboardEntryDto.toDto(i + 1, allEntriesAsEntities[i]);
    }

    List<LeaderboardEntryDto> leaderboardEntryDtos = new ArrayList<>();
    for (int j = dtoObjects.length - 1; j >= 0; j--) {
      leaderboardEntryDtos.add(dtoObjects[j]);
    }

    Collections.sort(
        leaderboardEntryDtos,
        new Comparator<LeaderboardEntryDto>() {
          public int compare(LeaderboardEntryDto e1, LeaderboardEntryDto e2) {
            return e1.getPosition() - e2.getPosition();
          }
        });

    return leaderboardEntryDtos;
  }
}
