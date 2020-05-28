package org.springworks.entity;

public class TripReport {
    public final double distanceSpeeding;
    public final double durationSpeeding;
    public final double totalDuration;
    public final double totalDistance;

    public TripReport(double distanceSpeeding, double durationSpeeding, double totalDuration, double totalDistance) {
        this.distanceSpeeding = distanceSpeeding;
        this.durationSpeeding = durationSpeeding;
        this.totalDuration = totalDuration;
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