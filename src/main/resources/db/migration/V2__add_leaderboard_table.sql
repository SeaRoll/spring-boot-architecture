CREATE TABLE IF NOT EXISTS leaderboard_entry (
    id UUID PRIMARY KEY,
    nick VARCHAR(20) NOT NULL,
    score INT NOT NULL
)
