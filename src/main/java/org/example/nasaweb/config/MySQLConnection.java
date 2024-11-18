package org.example.nasaweb.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private Connection connection;
    private static MySQLConnection instance;

    private MySQLConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection("jdbc:mysql://mysql_db_nasaWeb:3306/nasa", "root", "root");
            System.out.println("Connected to MySQL database");
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database: " + e.getMessage(), e);
        }
    }

    public static synchronized MySQLConnection getInstance() {
        if (instance == null) {
            instance = new MySQLConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Reabrir la conexión si está cerrada o nula
                connection = DriverManager.getConnection("jdbc:mysql://mysql_db_nasaWeb:3306/nasa", "root", "root");
                System.out.println("Reconnected to MySQL database");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the database connection: " + e.getMessage(), e);
        }
        return connection;
    }


    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from MySQL database");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error disconnecting from the database: " + e.getMessage(), e);
        }
    }
}
