package org.springworks.entity;

public class TripReport {
    private double distanceSpeeding;
    private double durationSpeeding;
    private double totalDuration;
    private double totalDistance;

    public double getDistanceSpeeding() {
        return distanceSpeeding;
    }

    public void setDistanceSpeeding(double distanceSpeeding) {
        this.distanceSpeeding = distanceSpeeding;
    }

    public double getDurationSpeeding() {
        return durationSpeeding;
    }

    public void setDurationSpeeding(double durationSpeeding) {
        this.durationSpeeding = durationSpeeding;
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(double totalDuration) {
        this.totalDuration = totalDuration;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    @Override
    public String toString() {
        return "TripReport{" +
                "distanceSpeeding='" + distanceSpeeding + " meters"+'\'' +
                ", durationSpeeding='" + durationSpeeding +" seconds" + '\'' +
                ", totalDuration='" + totalDuration + " seconds"+'\'' +
                ", totalDistance='" + totalDistance + " meters"+ '\'' +
                '}';
    }
}