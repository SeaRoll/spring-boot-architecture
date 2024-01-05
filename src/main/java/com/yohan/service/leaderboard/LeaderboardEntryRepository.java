package com.yohan.service.leaderboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderboardEntryRepository extends JpaRepository<LeaderboardEntry, String> {}
