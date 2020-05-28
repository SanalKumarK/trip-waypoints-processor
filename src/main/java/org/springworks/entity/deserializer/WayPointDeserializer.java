package org.springworks.entity.deserializer;

import com.google.gson.*;
import org.springworks.entity.Position;
import org.springworks.entity.WayPoint;

import java.lang.reflect.Type;
import java.time.Instant;

public class WayPointDeserializer implements JsonDeserializer<WayPoint> {

    @Override
    public WayPoint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String timeStamp = jsonObject.get("timestamp").getAsString();
        double speed = jsonObject.get("speed").getAsDouble();
        double speedLimit = jsonObject.get("speed_limit").getAsDouble();

        JsonObject posJsonObj = jsonObject.getAsJsonObject("position");
        double latitude = posJsonObj.get("latitude").getAsDouble();
        double longitude = posJsonObj.get("longitude").getAsDouble();

        Position position = new Position(latitude, longitude);
        Instant time = Instant.parse(timeStamp);
        return new WayPoint(time, position, speed, speedLimit);
    }
}