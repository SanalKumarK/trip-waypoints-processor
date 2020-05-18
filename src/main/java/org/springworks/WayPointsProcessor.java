package org.springworks;

import org.springworks.entity.TripReport;
import org.springworks.entity.WayPoint;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WayPointsProcessor {

    /**
     * Process the list of waypoints and generate TripReport.
     *
     * @param wayPoints
     * @return TripReport containing result of the trip.
     */
    public TripReport process(List<WayPoint> wayPoints) {
        if (wayPoints == null || wayPoints.isEmpty() || wayPoints.size() == 1) {
            return null;
        }

        TripReport report = sortWayPoints(wayPoints).stream()
                .collect(WayPointEvaluator::new, WayPointEvaluator::accept, WayPointEvaluator::combine)
                .getReport();
        report.setTotalDuration(getTotalDuration(wayPoints));
        return report;
    }

    /**
     * Sort the waypoints based on the Timestamp.
     *
     * @param wayPoints
     * @return timestamp sorted list of waypoints
     */
    private List<WayPoint> sortWayPoints(List<WayPoint> wayPoints) {
        return wayPoints.stream()
                .sorted(Comparator.comparing(WayPoint::getTimestamp))
                .collect(Collectors.toList());
    }

    /**
     * Get total duration of the trip.
     *
     * @param wayPoints
     * @return duration of the trip in seconds.
     */
    private long getTotalDuration(List<WayPoint> wayPoints) {
        if (wayPoints.size() >= 2) {
            Instant start = wayPoints.get(0).getTimestamp().toInstant();
            Instant last = wayPoints.get(wayPoints.size() - 1).getTimestamp().toInstant();
            return (Duration.between(start, last).getSeconds());
        }
        return 0;
    }
}