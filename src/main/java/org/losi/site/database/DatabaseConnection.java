package org.losi.site.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void connect(String dbPath) throws SQLException {
        String url = "jdbc:sqlite:" + dbPath;
        connection = DriverManager.getConnection(url);
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}