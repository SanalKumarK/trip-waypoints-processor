package org.springworks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class FileProcessorTest {

    FileProcessor fileProcessor = new FileProcessor();

    @Test
    void getWayPoints() {
        Assertions.assertNotNull(fileProcessor.getWayPointsFromResource("waypoints.json"));
    }

    @Test
    void getWayPointsFileEmpty() {
        Assertions.assertEquals(Collections.emptyList(),
                fileProcessor.getWayPointsFromResource("waypoints_empty.json"));
    }

    @Test
    void getWayPointsFileOneWayPoint() {
        Assertions.assertNotNull(fileProcessor.getWayPointsFromResource("waypoints_1.json"));
        Assertions.assertTrue(fileProcessor.getWayPointsFromResource("waypoints_1.json").size()==1);
    }

    @Test
    void getWayPointsFileNotExist() {
        Assertions.assertTrue(fileProcessor.getWayPointsFromResource("waypoints_2.json").isEmpty());
    }
}