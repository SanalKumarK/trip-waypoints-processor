package org.springworks;

import org.springworks.entity.TripReport;
import org.springworks.entity.WayPoint;

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

        WayPointAggregator wayPointAggregator = new WayPointAggregator();
        sortWayPoints(wayPoints).stream()
                .reduce((wayPoint, wayPoint2) -> {
                    wayPointAggregator.reduce(wayPoint, wayPoint2);
                    return wayPoint2;
                });

        TripReport tripReport = new TripReport(wayPointAggregator.getDistanceSpeeding(),
                wayPointAggregator.getDurationSpeeding(),
                wayPointAggregator.getTotalDuration(),
                wayPointAggregator.getTotalDistance());
        return tripReport;
    }

    /**
     * Sort the waypoints based on the Timestamp.
     *
     * @param wayPoints
     * @return timestamp sorted list of waypoints
     */
    private List<WayPoint> sortWayPoints(List<WayPoint> wayPoints) {
        return wayPoints.stream()
                .sorted(Comparator.comparing(wayPoint -> wayPoint.timestamp))
                .collect(Collectors.toList());
    }
}