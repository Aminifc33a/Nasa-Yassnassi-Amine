package org.example.nasaweb.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.example.nasaweb.dao.AsteroidDao;
import org.example.nasaweb.dao.JDBC.AsteroidDaoImpl;
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
    private AsteroidDao asteroidDao;
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

                    Asteroid asteroid = new Asteroid();
                    asteroid.setId(asteroidJson.get("id").getAsLong());
                    asteroid.setName(asteroidJson.get("name").getAsString());
                    asteroid.setAbsoluteMagnitude(asteroidJson.get("absolute_magnitude_h").getAsBigDecimal());
                    asteroid.setDiameterKmAverage(asteroidJson.getAsJsonObject("estimated_diameter")
                            .getAsJsonObject("meters").get("estimated_diameter_max").getAsBigDecimal());
                    asteroid.setIsPotentiallyHazardous(asteroidJson.get("is_potentially_hazardous_asteroid").getAsBoolean());

                    // Inicializar la lista de acercamientos
                    asteroid.setApproaches(new ArrayList<>());

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

                        asteroid.getApproaches().add(approach);
                    }

                    asteroids.add(asteroid);
                }
            }

            return asteroids;
        }
    }

    public void syncAsteroidsToDatabase() {
        asteroidDao = new AsteroidDaoImpl();
        try {
            // Obtener los asteroides desde la API
            List<Asteroid> nasaAsteroids = fetchAsteroidsFromAPI();

            for (Asteroid asteroid : nasaAsteroids) {
                // Verificar si el asteroide ya existe en la base de datos
                if (asteroidDao.findById(asteroid.getId()) == null) {
                    // Establecer la relación bidireccional
                    for (Approach approach : asteroid.getApproaches()) {
                        approach.setAsteroid(asteroid);
                    }

                    // Persistir el asteroide (incluye los acercamientos gracias al cascade)
                    asteroidDao.create(asteroid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
