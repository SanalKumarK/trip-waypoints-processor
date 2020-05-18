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
        Assertions.assertEquals(67.67430454853171, report.getDistanceSpeeding());
        Assertions.assertEquals(5.0, report.getDurationSpeeding());
        Assertions.assertEquals(20.0, report.getTotalDuration());
        Assertions.assertEquals(201.132793549162, report.getTotalDistance());
    }

    @Test
    void processTripSizeX() {
        TripReport report = wayPointsProcessor.process(createWayPointList(500));
        Assertions.assertNotNull(report);
        Assertions.assertTrue(report.getDistanceSpeeding()>0);
        Assertions.assertTrue(report.getDurationSpeeding()>0);
        Assertions.assertEquals(2495.0, report.getTotalDuration());
        Assertions.assertEquals(62191.87683385613, report.getTotalDistance());
    }

    @Test
    void processTripSizeX2() {
        TripReport report = wayPointsProcessor.process(createWayPointList(1000));
        System.out.println(report);
        Assertions.assertTrue(report.getDistanceSpeeding()>0);
        Assertions.assertTrue(report.getDurationSpeeding()>0);
        Assertions.assertEquals(4995.0, report.getTotalDuration());
        Assertions.assertEquals(124320.23624504519, report.getTotalDistance());
    }

    @Test
    void processTripSizeX3() {
        TripReport report = wayPointsProcessor.process(createWayPointList(3000));
        Assertions.assertNotNull(report);
        System.out.println(report);
        Assertions.assertTrue(report.getDistanceSpeeding()>0);
        Assertions.assertTrue(report.getDurationSpeeding()>0);
        Assertions.assertEquals(14995.0, report.getTotalDuration());
        Assertions.assertEquals(370978.06097808626, report.getTotalDistance());
    }

    private List<WayPoint> createWayPointList(int size) {
        List<WayPoint> wayPointList = new ArrayList<>();
        double latitude = 59.334;
        double longitude = 18.0667;
        Instant now = Instant.now();
        for(int i=0;i<size;i++) {
            WayPoint wayPoint = new WayPoint();
            Position position = new Position();
            position.setLatitude(latitude += .001);
            position.setLongitude(longitude+=.001);
            wayPoint.setPosition(position);
            wayPoint.setSpeed(ThreadLocalRandom.current().nextInt(40));
            wayPoint.setSpeed_limit(30);
            now = now.plusSeconds(5);
            wayPoint.setTimestamp(Date.from(now));
            wayPointList.add(wayPoint);
        }
        return wayPointList;
    }
}