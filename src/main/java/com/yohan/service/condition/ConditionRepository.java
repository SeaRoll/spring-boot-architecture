package com.yohan.service.condition;

import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConditionRepository extends JpaRepository<Condition, Instant> {

  @Query(
      value =
          """
      SELECT
        time_bucket(:interval\\:\\:INTERVAL, time)\\:\\:TIMESTAMP AS bucket,
        AVG((value->>(:key)\\:\\:text)\\:\\:numeric) AS avgValue
      FROM conditions
      WHERE company = :company
        AND time >= :earliestDate
        AND time <= :latestDate
      GROUP BY bucket
      ORDER BY bucket ASC;
  """,
      nativeQuery = true)
  List<GroupedCondition> getAvgValuePerDay(
      String interval, String key, String company, Instant earliestDate, Instant latestDate);

  @Query(
      value =
          """
      EXPLAIN ANALYZE
      SELECT
        time_bucket(:interval\\:\\:INTERVAL, time)\\:\\:TIMESTAMP AS bucket,
        AVG((value->>(:key)\\:\\:text)\\:\\:numeric) AS avgValue
      FROM conditions
      WHERE company = :company
        AND device = :device
        AND time >= :earliestDate
        AND time <= :latestDate
      GROUP BY bucket
      ORDER BY bucket ASC;
  """,
      nativeQuery = true)
  List<String> explainGetAvgValueDevicePerDay(
      String interval,
      String key,
      String company,
      String device,
      Instant earliestDate,
      Instant latestDate);

  interface GroupedCondition {
    Instant getBucket();

    Double getAvgValue();
  }
}
