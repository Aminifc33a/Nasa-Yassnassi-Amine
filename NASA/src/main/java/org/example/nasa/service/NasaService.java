package org.example.nasa.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.nasa.dao.ApproachDao;
import org.example.nasa.dao.AsteroidDao;
import org.example.nasa.model.Approach;
import org.example.nasa.model.Asteroid;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NasaService {
    private final AsteroidDao asteroidDao;
    private final ApproachDao approachDao;
    private final Gson gson = new Gson();

    public NasaService(AsteroidDao asteroidDao, ApproachDao approachDao) {
        this.asteroidDao = asteroidDao;
        this.approachDao = approachDao;
    }

    public void syncAsteroids() throws IOException {
        URL url = new URL("https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=AdeKAyAyJra0WggeuZiYtJAkKZZ3svVo4YnDa1vh");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            StringBuilder jsonStr = new StringBuilder();
            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                while (scanner.hasNext()) {
                    jsonStr.append(scanner.nextLine());
                }
            }
            JsonObject response = gson.fromJson(jsonStr.toString(), JsonObject.class);
            JsonArray asteroidArray = response.getAsJsonArray("near_earth_objects");
            for (JsonElement asteroidJson : asteroidArray) {
                JsonObject asteroidObject = asteroidJson.getAsJsonObject();
                Asteroid asteroid = parseAsteroid(asteroidObject);
                if (asteroidDao.findById(asteroid.getId()) == null) {
                    asteroidDao.save(asteroid);
                }
                List<Approach> approaches = parseApproaches(asteroidObject, asteroid);
                approaches.forEach(approachDao::save);
            }
        }
    }

    private Asteroid parseAsteroid(JsonObject json) {
        Asteroid asteroid = new Asteroid();
        asteroid.setId(json.get("id").getAsString());
        asteroid.setName(json.get("name").getAsString());
        asteroid.setMagnitude(json.get("absolute_magnitude_h").getAsDouble());

        JsonObject diameter = json.getAsJsonObject("estimated_diameter").getAsJsonObject("kilometers");
        double minDiameter = diameter.get("estimated_diameter_min").getAsDouble();
        double maxDiameter = diameter.get("estimated_diameter_max").getAsDouble();
        asteroid.setDiameter(minDiameter + (maxDiameter - minDiameter) / 2);

        asteroid.setPotentiallyHazardous(json.get("is_potentially_hazardous").getAsBoolean());

        return asteroid;
    }

    private List<Approach> parseApproaches(JsonObject json, Asteroid asteroid) {
        List<Approach> approaches = new ArrayList<>();
        JsonArray approachDataArray = json.getAsJsonArray("close_approach_data");
        for (JsonElement elem : approachDataArray) {
            JsonObject approachData = elem.getAsJsonObject();
            Approach approach = new Approach();
            approach.setAsteroid(asteroid);
            approach.setApproachDate(approachData.get("close_approach_date").getAsString());

            JsonObject relativeVelocity = approachData.getAsJsonObject("relative_velocity");
            approach.setVelocity(relativeVelocity.get("kilometers_per_second").getAsDouble());

            JsonObject missDistance = approachData.getAsJsonObject("miss_distance");
            approach.setDistance(missDistance.get("kilometers").getAsDouble());

            approach.setOrbitingBody(approachData.get("orbiting_body").getAsString());
            approaches.add(approach);
        }
        return approaches;
    }
}
