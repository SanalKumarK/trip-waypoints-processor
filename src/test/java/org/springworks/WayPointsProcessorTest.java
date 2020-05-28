package org.springworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springworks.entity.Position;
import org.springworks.entity.TripReport;
import org.springworks.entity.WayPoint;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class WayPointsProcessorTest {

    WayPointsProcessor wayPointsProcessor = new WayPointsProcessor();

    @Test
    void processTrip() {
        FileProcessor processor = new FileProcessor();
        List<WayPoint> wayPointsFromResource = processor.getWayPointsFromResource("waypoints.json");
        Assertions.assertNotNull(wayPointsFromResource);
        TripReport report = wayPointsProcessor.process(wayPointsFromResource);
        Assertions.assertNotNull(report);
        System.out.println(report);
        Assertions.assertEquals(67.67430454853171, report.distanceSpeeding);
        Assertions.assertEquals(5.0, report.durationSpeeding);
        Assertions.assertEquals(20.0, report.totalDuration);
        Assertions.assertEquals(201.132793549162, report.totalDistance);
    }

    @Test
    void processTripSizeX() {
        TripReport report = wayPointsProcessor.process(createWayPointList(500));
        Assertions.assertNotNull(report);
        Assertions.assertTrue(report.distanceSpeeding>0);
        Assertions.assertTrue(report.durationSpeeding>0);
        Assertions.assertEquals(2495.0, report.totalDuration);
        Assertions.assertEquals(62191.87683385613, report.totalDistance);
    }

    @Test
    void processTripSizeX2() {
        TripReport report = wayPointsProcessor.process(createWayPointList(1000));
        System.out.println(report);
        Assertions.assertTrue(report.distanceSpeeding>0);
        Assertions.assertTrue(report.durationSpeeding>0);
        Assertions.assertEquals(4995.0, report.totalDuration);
        Assertions.assertEquals(124320.23624504519, report.totalDistance);
    }

    @Test
    void processTripSizeX3() {
        TripReport report = wayPointsProcessor.process(createWayPointList(3000));
        Assertions.assertNotNull(report);
        System.out.println(report);
        Assertions.assertTrue(report.distanceSpeeding>0);
        Assertions.assertTrue(report.durationSpeeding>0);
        Assertions.assertEquals(14995.0, report.totalDuration);
        Assertions.assertEquals(370978.06097808626, report.totalDistance);
    }

    private List<WayPoint> createWayPointList(int size) {
        List<WayPoint> wayPointList = new ArrayList<>();
        double latitude = 59.334;
        double longitude = 18.0667;
        Instant now = Instant.now();
        for(int i=0;i<size;i++) {
            latitude += .001;
            longitude += .001;
            Position position = new Position(latitude, longitude);
            now = now.plusSeconds(5);
            WayPoint wayPoint = new WayPoint(now, position, ThreadLocalRandom.current().nextInt(40), 30);
            wayPointList.add(wayPoint);
        }
        return wayPointList;
    }
}