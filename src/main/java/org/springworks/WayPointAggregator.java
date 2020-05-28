package org.springworks;

import org.springworks.entity.WayPoint;

import java.time.Duration;

public class WayPointAggregator {

    private double totalDuration, totalDistance, distanceSpeeding, durationSpeeding;

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public double getDistanceSpeeding() {
        return distanceSpeeding;
    }

    public double getDurationSpeeding() {
        return durationSpeeding;
    }

    public void reduce(WayPoint w1, WayPoint w2) {
        double distance = getDistance(w1, w2);
        double duration = Duration.between(w1.timestamp, w2.timestamp).getSeconds();
        totalDuration += duration;
        totalDistance += distance;

        if (isSpeeding(w1) && isSpeeding(w2)) {
            distanceSpeeding += distance;
            durationSpeeding += duration;
        }
    }

    private boolean isSpeeding(WayPoint wayPoint) {
        return wayPoint.speed > wayPoint.speedLimit;
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
    private double getDistance(WayPoint w1, WayPoint w2) {
        double lat1 = w1.position.latitude, lat2 = w2.position.latitude,
                lon1 = w1.position.longitude, lon2 = w2.position.longitude;
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
