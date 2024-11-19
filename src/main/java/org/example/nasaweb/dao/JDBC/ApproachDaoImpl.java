package org.example.nasaweb.dao.JDBC;

import org.example.nasaweb.config.MySQLConnection;
import org.example.nasaweb.dao.ApproachDao;
import org.example.nasaweb.model.Approach;
import org.example.nasaweb.model.Asteroid;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApproachDaoImpl implements ApproachDao {
    private final MySQLConnection connectionManager;

    public ApproachDaoImpl() {
        connectionManager = MySQLConnection.getInstance();
    }

    @Override
    public void create(Approach approach, Asteroid asteroid) {
        Connection conn = connectionManager.getConnection();
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
    public List<Approach> findByAsteroidId(long asteroidId) {
        Connection conn = connectionManager.getConnection();
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
