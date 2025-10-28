package com.yohan.service.leaderboard;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class LeaderboardEntryDto {

  int position;
  String nick;
  int score;

  public static LeaderboardEntryDto toDto(int pos, LeaderboardEntry entity) {
    return LeaderboardEntryDto.builder()
        .position(pos)
        .nick(entity.getNick())
        .score(entity.getScore())
        .build();
  }
}
