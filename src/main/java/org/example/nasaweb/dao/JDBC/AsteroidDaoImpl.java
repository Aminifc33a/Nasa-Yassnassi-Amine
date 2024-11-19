package org.example.nasaweb.dao.JDBC;

import org.example.nasaweb.dao.AsteroidDao;
import org.example.nasaweb.model.Approach;
import org.example.nasaweb.model.Asteroid;
import org.example.nasaweb.config.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsteroidDaoImpl implements AsteroidDao {

    private final MySQLConnection connectionManager;

    public AsteroidDaoImpl() {
        connectionManager = MySQLConnection.getInstance();
    }

    public void create(Asteroid asteroid) {
        String query = "INSERT INTO Asteroids (id, name, absolute_magnitude, diameter_km_average, is_potentially_hazardous) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            System.out.println("Inserting asteroid: " + asteroid.getName());
            statement.setLong(1, asteroid.getId());
            statement.setString(2, asteroid.getName());
            statement.setBigDecimal(3, asteroid.getAbsoluteMagnitude());
            statement.setBigDecimal(4, asteroid.getDiameterKmAverage());
            statement.setBoolean(5, asteroid.getIsPotentiallyHazardous());

            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

            // Insertar los acercamientos para este asteroide
            for (Approach approach : asteroid.getApproaches()) {
                createApproach(conn, approach, asteroid);  // Llamada correcta con la conexión
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving asteroid: " + e.getMessage(), e);
        }
    }


    private void createApproach(Connection conn, Approach approach, Asteroid asteroid) {
        String query = "INSERT INTO Approaches (approach_date, velocity, distance, orbiting_body, asteroid_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setDate(1, Date.valueOf(approach.getApproachDate()));
            statement.setBigDecimal(2, approach.getVelocity());
            statement.setBigDecimal(3, approach.getDistance());
            statement.setString(4, approach.getOrbitingBody());
            statement.setLong(5, asteroid.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving approach: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Asteroid> findAll() {
        List<Asteroid> asteroids = new ArrayList<>();
        String query = "SELECT * FROM Asteroids";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Asteroid asteroid = new Asteroid();
                asteroid.setId(resultSet.getLong("id"));
                asteroid.setName(resultSet.getString("name"));
                asteroid.setDiameterKmAverage(resultSet.getBigDecimal("diameter_km_average"));
                asteroid.setIsPotentiallyHazardous(resultSet.getBoolean("is_potentially_hazardous"));
                asteroid.setAbsoluteMagnitude(resultSet.getBigDecimal("absolute_magnitude"));

                // Obtén los acercamientos usando la conexión existente
                List<Approach> approaches = findApproachesByAsteroidId(conn, asteroid.getId());
                asteroid.setApproaches(approaches);

                asteroids.add(asteroid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching all asteroids: " + e.getMessage(), e);
        }

        return asteroids;
    }

    @Override
    public Asteroid findById(long id) {
        String query = "SELECT * FROM Asteroids WHERE id = ?";
        Asteroid asteroid = null;

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                asteroid = new Asteroid();
                asteroid.setId(resultSet.getLong("id"));
                asteroid.setName(resultSet.getString("name"));
                asteroid.setDiameterKmAverage(resultSet.getBigDecimal("diameter_km_average"));
                asteroid.setIsPotentiallyHazardous(resultSet.getBoolean("is_potentially_hazardous"));
                asteroid.setAbsoluteMagnitude(resultSet.getBigDecimal("absolute_magnitude"));

                // Cargar los acercamientos para este asteroide
                List<Approach> approaches = findApproachesByAsteroidId(conn, asteroid.getId());
                asteroid.setApproaches(approaches);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching asteroid by id: " + e.getMessage(), e);
        }

        return asteroid;
    }

    @Override
    public void deleteAll() {
        //borrar todo de la tabla Asteroids y Approaches
        String queryAsteroids = "DELETE FROM Asteroids";
        String queryApproaches = "DELETE FROM Approaches";

        try (Connection conn = connectionManager.getConnection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(queryApproaches);
            statement.executeUpdate(queryAsteroids);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting all asteroids and approaches: " + e.getMessage(), e);
        }
    }

    private List<Approach> findApproachesByAsteroidId(Connection conn, long asteroidId) {
        List<Approach> approaches = new ArrayList<>();
        String query = "SELECT * FROM Approaches WHERE asteroid_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setLong(1, asteroidId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Approach approach = new Approach();
                    approach.setApproachDate(resultSet.getDate("approach_date").toLocalDate());
                    approach.setVelocity(resultSet.getBigDecimal("velocity"));
                    approach.setDistance(resultSet.getBigDecimal("distance"));
                    approach.setOrbitingBody(resultSet.getString("orbiting_body"));
                    approaches.add(approach);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching approaches by asteroid id: " + e.getMessage(), e);
        }

        return approaches;
    }

}
