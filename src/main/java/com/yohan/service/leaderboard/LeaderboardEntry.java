package com.yohan.service.leaderboard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leaderboard_entry")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardEntry {

  @Id
  @Column(name = "id")
  @Getter
  private UUID id;

  @Column(name = "nick")
  @Getter
  private String nick;

  @Column(name = "score")
  @Getter
  private Integer score;

  public static LeaderboardEntry of(String nick, Integer score) {
    return LeaderboardEntry.builder()
      .id(UUID.randomUUID())
      .nick(nick)
      .score(score)
      .build();
  }
}
