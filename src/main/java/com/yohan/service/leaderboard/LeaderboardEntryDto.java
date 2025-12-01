package com.yohan.service.leaderboard;

import java.util.UUID;

public record LeaderboardEntryDto(UUID id, String nick, Integer score) {}
