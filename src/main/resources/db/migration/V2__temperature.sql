CREATE TABLE conditions
(
    time    TIMESTAMP NOT NULL,
    company TEXT      NOT NULL,
    device  TEXT      NOT NULL,
    value   JSONB     NOT NULL
);

SELECT create_hypertable('conditions', by_range('time'));

-- https://docs.timescale.com/use-timescale/latest/schema-management/about-indexing/
CREATE INDEX ON conditions (company, time DESC);
CREATE INDEX ON conditions (company, device, time DESC);
