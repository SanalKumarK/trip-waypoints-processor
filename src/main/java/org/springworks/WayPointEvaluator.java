package org.springworks;

import org.springworks.entity.TripReport;
import org.springworks.entity.WayPoint;

import java.time.Duration;
import java.util.function.Consumer;

public class WayPointEvaluator implements Consumer<WayPoint> {

    private WayPoint wayPoint;
    private double totalDistance, speedingDistance, speedingDuration;
    boolean speeding = false;
    private WayPoint speedingStart = null, lastReportedSpeeding = null;

    /**
     * Generate the TripReport with the details.
     * @return TripReport
     */
    public TripReport getReport() {
        TripReport tripReport = new TripReport();
        tripReport.setDistanceSpeeding(speedingDistance);
        tripReport.setDurationSpeeding(speedingDuration);
        tripReport.setTotalDistance(totalDistance);
        return tripReport;
    }

    @Override
    public void accept(WayPoint other) {
        calculateSpeeding(other);
        calculateTotalDistance(other);
        this.wayPoint = other;
    }

    /**
     * Calculate the distance from each waypoint.
     * @param other
     */
    private void calculateTotalDistance(WayPoint other) {
        if (this.wayPoint == null) {
            return;
        }
        totalDistance += getDistance(wayPoint.getPosition().getLatitude(), other.getPosition().getLatitude(),
                wayPoint.getPosition().getLongitude(), other.getPosition().getLongitude());
    }

    /**
     * Check the waypoint is speeding or normal,
     * if we speeding calculate the speeding metrics.
     * @param other
     */
    private void calculateSpeeding(WayPoint other) {
        if (other.getSpeed() > other.getSpeed_limit()) {
            if (speeding) {
                lastReportedSpeeding = other;
            } else {
                speeding = true;
                speedingStart = other;
            }
        } else {
            speeding = false;
            calculateSpeedingMetrics(speedingStart, lastReportedSpeeding);
            speedingStart = null;
            lastReportedSpeeding = null;
        }
    }

    /**
     * Calculate the speeding metrics from the given way points.
     * @param speedingStart Speeding started waypoint
     * @param lastReportedSpeeding Speeding ended waypoint.
     */
    private void calculateSpeedingMetrics(WayPoint speedingStart, WayPoint lastReportedSpeeding) {
        if (speedingStart != null && lastReportedSpeeding != null) {
            speedingDistance += getDistance(speedingStart.getPosition().getLatitude(), lastReportedSpeeding.getPosition().getLatitude(),
                    speedingStart.getPosition().getLongitude(), lastReportedSpeeding.getPosition().getLongitude());

            speedingDuration += Duration.between(speedingStart.getTimestamp().toInstant(),
                    lastReportedSpeeding.getTimestamp().toInstant()).getSeconds();
        }
    }

    public void combine(WayPointEvaluator distanceEvaluator) {
        if (distanceEvaluator != null) {
            speedingDistance += distanceEvaluator.speedingDistance;
            speedingDuration += distanceEvaluator.speedingDuration;
        }
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * <p>
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     *
     * @returns Distance in Meters
     */
    private double getDistance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);
        return Math.sqrt(distance);
    }
}
