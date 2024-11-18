package org.example.nasaweb.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.nasaweb.model.Asteroid;
import org.example.nasaweb.model.Approach;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class NasaAPIService {
    private static final String API_URL = "https://api.nasa.gov/neo/rest/v1/feed";
    private static final String API_KEY = "AdeKAyAyJra0WggeuZiYtJAkKZZ3svVo4YnDa1vh";

    public List<Asteroid> fetchAsteroidsFromAPI() throws IOException {
        List<Asteroid> asteroids = new ArrayList<>();

        // Definir el rango de fechas
        String startDate = "2023-01-01";
        String endDate = "2023-01-07";

        // Construir la URL
        String urlString = String.format("%s?start_date=%s&end_date=%s&api_key=%s", API_URL, startDate, endDate, API_KEY);
        URL url = new URL(urlString);

        // Establecer la conexión
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
            // Leer la respuesta de la API
            StringBuilder response = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                response.append((char) c);
            }

            // Parsear la respuesta JSON
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(response.toString(), JsonObject.class);
            JsonObject nearEarthObjects = root.getAsJsonObject("near_earth_objects");

            // Procesar los datos por fecha
            for (String date : nearEarthObjects.keySet()) {
                JsonArray asteroidsArray = nearEarthObjects.getAsJsonArray(date);

                for (JsonElement asteroidElement : asteroidsArray) {
                    JsonObject asteroidJson = asteroidElement.getAsJsonObject();

                    // Mapear datos del asteroide
                    Asteroid asteroid = new Asteroid();
                    asteroid.setId(asteroidJson.get("id").getAsLong());
                    asteroid.setName(asteroidJson.get("name").getAsString());
                    asteroid.setDiameterKmAverage(asteroidJson.getAsJsonObject("estimated_diameter")
                            .getAsJsonObject("meters").get("estimated_diameter_max").getAsBigDecimal());
                    asteroid.setIsPotentiallyHazardous(asteroidJson.get("is_potentially_hazardous_asteroid").getAsBoolean());

                    // Asegúrate de obtener el absolute_magnitude correctamente
                    if (asteroidJson.has("absolute_magnitude_h")) {
                        asteroid.setAbsoluteMagnitude(asteroidJson.get("absolute_magnitude_h").getAsBigDecimal());
                    }

                    // Mapear datos de los acercamientos
                    List<Approach> approaches = new ArrayList<>();
                    JsonArray closeApproachData = asteroidJson.getAsJsonArray("close_approach_data");

                    for (JsonElement approachElement : closeApproachData) {
                        JsonObject approachJson = approachElement.getAsJsonObject();

                        Approach approach = new Approach();
                        approach.setApproachDate(LocalDate.parse(approachJson.get("close_approach_date").getAsString()));
                        approach.setVelocity(approachJson.getAsJsonObject("relative_velocity")
                                .get("kilometers_per_hour").getAsBigDecimal());
                        approach.setDistance(approachJson.getAsJsonObject("miss_distance")
                                .get("kilometers").getAsBigDecimal());
                        approach.setOrbitingBody(approachJson.get("orbiting_body").getAsString());
                        approach.setAsteroid(asteroid);  // Establecer la referencia al asteroide

                        approaches.add(approach);
                    }

                    asteroid.setApproaches(approaches);
                    asteroids.add(asteroid);
                }
            }
        }

        return asteroids;
    }

    public void syncAsteroidsToDatabase() {
        try {
            List<Asteroid> asteroidsFromAPI = fetchAsteroidsFromAPI();
            AsteroidService asteroidService = new AsteroidService();

            for (Asteroid asteroid : asteroidsFromAPI) {
                Asteroid existingAsteroid = asteroidService.findById(asteroid.getId());

                if (existingAsteroid == null) {
                    asteroidService.create(asteroid);  // Esto ahora incluye la inserción de acercamientos
                    System.out.println("Inserted asteroid and approaches: " + asteroid.getName());
                } else {
                    System.out.println("Asteroid already exists in the database: " + asteroid.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
