package org.example.nasaweb.dao.JDBC;

import org.example.nasaweb.dao.ApproachDao;
import org.example.nasaweb.dao.AsteroidDao;
import org.example.nasaweb.model.Approach;
import org.example.nasaweb.model.Asteroid;
import org.example.nasaweb.config.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsteroidDaoImpl implements AsteroidDao {

    private final MySQLConnection connectionManager;
    private final ApproachDao approachDao;

    public AsteroidDaoImpl() {
        connectionManager = MySQLConnection.getInstance();
        approachDao = new ApproachDaoImpl();
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
                approachDao.create(approach, asteroid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving asteroid: " + e.getMessage(), e);
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
                mapAsteroid(resultSet, asteroid);

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
                mapAsteroid(resultSet, asteroid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching asteroid by id: " + e.getMessage(), e);
        }

        return asteroid;
    }

    @Override
    public void deleteById(long id) {
        String query = "DELETE FROM Asteroids WHERE id =?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            System.out.println("Deleting asteroid by id: " + id);
            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting asteroid by id: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Asteroid asteroid) {
        String query = "UPDATE Asteroids SET name =?, absolute_magnitude =?, diameter_km_average =?, is_potentially_hazardous =? WHERE id =?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            System.out.println("Updating asteroid: " + asteroid.getName());
            statement.setString(1, asteroid.getName());
            statement.setBigDecimal(2, asteroid.getAbsoluteMagnitude());
            statement.setBigDecimal(3, asteroid.getDiameterKmAverage());
            statement.setBoolean(4, asteroid.getIsPotentiallyHazardous());
            statement.setLong(5, asteroid.getId());
            int rowsAffected = statement.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM Asteroids";

        try (Connection conn = connectionManager.getConnection();
             Statement statement = conn.createStatement()) {

            System.out.println("Deleting all asteroids...");
            int rowsAffected = statement.executeUpdate(query);
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting all asteroids: " + e.getMessage(), e);
        }
    }

    private void mapAsteroid(ResultSet resultSet, Asteroid asteroid) throws SQLException {
        asteroid.setId(resultSet.getLong("id"));
        asteroid.setName(resultSet.getString("name"));
        asteroid.setDiameterKmAverage(resultSet.getBigDecimal("diameter_km_average"));
        asteroid.setIsPotentiallyHazardous(resultSet.getBoolean("is_potentially_hazardous"));
        asteroid.setAbsoluteMagnitude(resultSet.getBigDecimal("absolute_magnitude"));

        List<Approach> approaches = approachDao.findByAsteroidId(asteroid.getId());
        asteroid.setApproaches(approaches);
    }
}
