package org.example.nasaweb.dao.JDBC;

import org.example.nasaweb.dao.UserDao;
import org.example.nasaweb.model.User;
import org.example.nasaweb.config.MySQLConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private final MySQLConnection connectionManager;

    public UserDaoImpl() {
        this.connectionManager = MySQLConnection.getInstance();
    }

    @Override
    public void create(User user) {
        String query = "INSERT INTO Users (username, role,password) VALUES (?,?, ?)";
        Connection conn = connectionManager.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query)) {

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getRole());
            statement.setString(3, hashedPassword);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating user: " + e.getMessage(), e);
        }
    }

    @Override
    public User getUser(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = ?";
        Connection conn = connectionManager.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");

                    if (BCrypt.checkpw(password, storedPassword)) {
                        User user = new User();
                        user.setId(resultSet.getLong("id"));
                        user.setUsername(resultSet.getString("username"));
                        user.setPassword(storedPassword);
                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching user: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        String query = "SELECT COUNT(*) AS count FROM Users WHERE username = ?";
        Connection conn = connectionManager.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("count") > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error checking username existence: " + e.getMessage(), e);
        }
        return false;
    }
}
