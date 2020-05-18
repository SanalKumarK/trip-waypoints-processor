package org.springworks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springworks.entity.WayPoint;

import java.io.File;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileProcessor {
    /**
     * Read the given filename from resources and returns List of waypoints in the file.
     * @param fileName
     * @return List of WayPoint or Empty list.
     */
    public List<WayPoint> getWayPoints(String fileName) {
        File file = new File(fileName);
        if (file == null) {
            System.out.println("File Not Found: " + fileName);
            return Collections.EMPTY_LIST;
        }
        return readFile(file);
    }

    /**
     * Read the given filename from resources and returns List of waypoints in the file.
     * @param fileName
     * @return List of WayPoint or Empty list.
     */
    public List<WayPoint> getWayPointsFromResource(String fileName) {
        File file = getResource(fileName);
        if (file == null) {
            System.out.println("File Not Found: " + fileName);
            return Collections.EMPTY_LIST;
        }
        return readFile(file);
    }

    private List<WayPoint> readFile(File file) {
        try (Reader reader = Files.newBufferedReader(Paths.get(file.getPath()))) {
            return new Gson().fromJson(reader, new TypeToken<List<WayPoint>>() {}.getType());
        } catch (Exception ex) {
            System.out.println("Error in reading file content: " + ex.getMessage());
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * Read the file from the resources folder.
     * @param fileName
     * @return
     */
    private File getResource(String fileName) {
        URL resource = ClassLoader.getSystemClassLoader().getResource(fileName);
        if (resource != null) {
            return new File(resource.getFile());
        }
        return null;
    }
}