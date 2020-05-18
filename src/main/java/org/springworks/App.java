package org.springworks;

import org.springworks.entity.WayPoint;

import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.err.println("Usage: java -jar App <absolute_file_path> \n");
            System.exit(1);
        }

        FileProcessor fileProcessor =  new FileProcessor();
        List<WayPoint> wayPoints = fileProcessor.getWayPoints(args[0]);
        System.out.println(new WayPointsProcessor().process(wayPoints));
    }
}