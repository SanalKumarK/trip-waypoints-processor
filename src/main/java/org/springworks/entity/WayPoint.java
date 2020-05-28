package org.springworks.entity;

import java.time.Instant;

public class WayPoint {
    public final Instant timestamp;
    public final Position position;
    public final double speed;
    public final double speedLimit;

    public WayPoint(Instant instant, Position position, double speed, double speedLimit) {
        this.timestamp = instant;
        this.position = position;
        this.speed = speed;
        this.speedLimit = speedLimit;
    }

    @Override
    public String toString() {
        return "WayPoint{" +
                "timestamp=" + timestamp +
                ", position=" + position +
                ", speed=" + speed +
                ", speed_limit=" + speedLimit +
                '}';
    }
}