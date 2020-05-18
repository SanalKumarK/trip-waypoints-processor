package org.springworks.entity;

import java.util.Date;

public class WayPoint {
    private Date timestamp;
    private Position position;
    private double speed;
    private double speed_limit;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed_limit() {
        return speed_limit;
    }

    public void setSpeed_limit(double speed_limit) {
        this.speed_limit = speed_limit;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "WayPoint{" +
                "timestamp=" + timestamp +
                ", position=" + position +
                ", speed=" + speed +
                ", speed_limit=" + speed_limit +
                '}';
    }
}