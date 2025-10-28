package com.yohan.service.leaderboard;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leaderboard_entry")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardEntry {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "nick")
  private String nick;

  @Column(name = "score")
  private Integer score;

  public LeaderboardEntry(String nick, Integer score) {
    this.id = UUID.randomUUID().toString();
    this.nick = nick;
    this.score = score;
  }
}
